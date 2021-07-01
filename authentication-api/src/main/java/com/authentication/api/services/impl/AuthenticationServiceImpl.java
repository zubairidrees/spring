package com.authentication.api.services.impl;

import com.authentication.api.dto.RequestUserDTO;
import com.authentication.api.dto.ResponseDTO;
import com.authentication.api.dto.ResponseUserDTO;
import com.authentication.api.entities.User;
import com.authentication.api.util.ResponseCodes;
import com.authentication.api.services.AuthenticationService;
import com.authentication.api.services.BlockBruteForceService;
import com.authentication.api.services.UserService;
import com.authentication.api.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * This service is responsible to identify and block brute force attempts,
 * and authentication based on provided user credentials
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserService userService;

    @Autowired
    BlockBruteForceService blockBruteForceService;

    @Autowired
    HttpServletRequest httpServletRequest;

    /**
     * This method authenticates a user and blocks brute force attempts
     *
     * @param requestUserDTO incoming credentials from request
     * @return response code and response message
     */
    @Override
    public ResponseDTO authenticateUser(RequestUserDTO requestUserDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
        if (Boolean.TRUE.equals(blockBruteForceService.isBruteForcedBlocked(getUserIPAddress()))) {
            return setAttemptsBlockedResponse(responseDTO);
        }

        try {
            User user = userService.findUser(requestUserDTO);
            return setSuccessResponse(responseDTO, user);
        } catch (BadCredentialsException badCredentialsException) {
            return setFailureResponse(responseDTO);
        }
    }

    private ResponseDTO setAttemptsBlockedResponse(ResponseDTO responseDTO) {

        responseDTO.setResponseCode(ResponseCodes.FORBIDDEN.getCode());
        responseDTO.setResponseMessage(ResponseCodes.FORBIDDEN.getDesc());

        return responseDTO;
    }

    private ResponseDTO setFailureResponse(ResponseDTO responseDTO) {

        responseDTO.setResponseMessage(ResponseCodes.UNAUTHORIZED.getDesc());
        responseDTO.setResponseCode(ResponseCodes.UNAUTHORIZED.getCode());

        blockBruteForceService.logFailedAttempt(getUserIPAddress());
        return responseDTO;
    }

    private ResponseDTO setSuccessResponse(ResponseDTO responseDTO, User user) {

        responseDTO.setResponseMessage(user.getUserName() + ResponseCodes.OK.getDesc());
        responseDTO.setResponseUserDTO(setResponseUserDTO(user));
        responseDTO.setResponseCode(ResponseCodes.OK.getCode());

        blockBruteForceService.logSuccessAttempt(getUserIPAddress());
        return responseDTO;
    }

    private ResponseUserDTO setResponseUserDTO(User user) {

        ResponseUserDTO responseUserDTO = new ResponseUserDTO();
        responseUserDTO.setUserEmail(user.getEmail());
        responseUserDTO.setUserName(user.getUserName());

        return responseUserDTO;
    }

    private String getUserIPAddress() {

        String xfHeader = httpServletRequest.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return httpServletRequest.getRemoteAddr();
        }

        return xfHeader.split(",")[0];
    }
}
