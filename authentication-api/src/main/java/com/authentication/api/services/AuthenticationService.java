package com.authentication.api.services;

import com.authentication.api.dto.RequestUserDTO;
import com.authentication.api.dto.ResponseDTO;

public interface AuthenticationService {

    ResponseDTO authenticateUser(RequestUserDTO requestUserDTO);
}
