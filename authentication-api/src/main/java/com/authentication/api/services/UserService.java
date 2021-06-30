package com.authentication.api.services;

import com.authentication.api.dto.RequestUserDTO;
import com.authentication.api.entities.User;

public interface UserService {

    User findUser(RequestUserDTO requestUserDTO);
}
