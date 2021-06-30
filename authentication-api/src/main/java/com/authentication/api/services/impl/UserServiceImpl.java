package com.authentication.api.services.impl;

import com.authentication.api.dto.RequestUserDTO;
import com.authentication.api.entities.User;
import com.authentication.api.services.UserService;
import com.authentication.api.util.Constants;
import com.authentication.api.util.ResponseCodes;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This service is responsible for finding the valid users
 * based on incoming credentials from user request
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * Finds Valid User
     * @param requestUserDTO contains user credentials
     * @return a valid user if found otherwise returns throws BadCredentialsException
     */
    @Override
    public User findUser(RequestUserDTO requestUserDTO) {


        Optional<User> user = getUserByUserNameAndPassword(requestUserDTO);

        if(user.isPresent() && credentialsMatched(user.get(),requestUserDTO)) {
            return user.get();
        }else{
            throw new BadCredentialsException(ResponseCodes.RESPONSE_UNAUTHORIZED.getDesc());
        }
    }

    /**
     * This is just a stub method which depicts fetching of a valid user
     */
    private Optional<User> getUserByUserNameAndPassword(RequestUserDTO requestUserDTO){
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword("admin123");
        user.setUserName("admin");
        return Optional.of(user);
    }

    private boolean credentialsMatched(User user, RequestUserDTO userDTO){
        return (user.getUserName().equalsIgnoreCase(userDTO.getUserName())
                && user.getPassword().equalsIgnoreCase(userDTO.getUserPassword()));
    }
}
