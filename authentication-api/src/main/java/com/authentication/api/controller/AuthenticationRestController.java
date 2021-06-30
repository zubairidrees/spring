package com.authentication.api.controller;

import com.authentication.api.dto.RequestUserDTO;
import com.authentication.api.dto.ResponseDTO;
import com.authentication.api.util.ResponseCodes;
import com.authentication.api.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationRestController {

    @Autowired
    AuthenticationService authenticationService;

    /**
     * This Rest API Responsible for authenticating a user
     * @param requestUserDTO incoming user request containing user credentials as a JSON Object
     * @return Http Response
     */
    @PostMapping(value = "/authenticate")
    public ResponseEntity<ResponseDTO> authenticateUser(@RequestBody RequestUserDTO requestUserDTO){

        ResponseDTO responseDTO = authenticationService.authenticateUser(requestUserDTO);

        if(responseDTO.getResponseCode().equals(HttpStatus.UNAUTHORIZED.value())){
            return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
        }else if(responseDTO.getResponseCode().equals(ResponseCodes.RESPONSE_FORBIDDEN.getCode())){
            return new ResponseEntity<>(responseDTO,HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
