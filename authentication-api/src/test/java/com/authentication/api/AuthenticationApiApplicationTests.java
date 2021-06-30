package com.authentication.api;

import com.authentication.api.dto.RequestUserDTO;
import com.authentication.api.dto.ResponseDTO;
import com.authentication.api.entities.User;
import com.authentication.api.services.BlockBruteForceService;
import com.authentication.api.services.UserService;
import com.authentication.api.services.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthenticationApiApplicationTests {

	@InjectMocks
	AuthenticationServiceImpl authenticationService;

	@Mock
	UserService userService;

	@Mock
	BlockBruteForceService blockBruteForceService;

	@Mock
	HttpServletRequest httpServletRequest;

	@Test
	void authenticateUserSuccess() {
		RequestUserDTO requestUserDTO = new RequestUserDTO();

		User user = new User();
		when(userService.findUser(requestUserDTO))
				.thenReturn(user);
		ResponseDTO responseDTO = authenticationService.authenticateUser(requestUserDTO);

		assertEquals(200, responseDTO.getResponseCode());
	}

	@Test()
	void authenticateUserFailure(){

		RequestUserDTO requestUserDTO = new RequestUserDTO();

		when(userService.findUser(requestUserDTO)).thenThrow(BadCredentialsException.class);

		ResponseDTO responseDTO = authenticationService.authenticateUser(requestUserDTO);

		assertEquals(401, responseDTO.getResponseCode());
	}




}
