package com.example.service;

import javax.validation.Valid;

import com.example.dto.UserRegisterRequest;
import com.example.model.User;

public interface UserService {

	Integer register(UserRegisterRequest userRegisterRequest);

	User getUserById(Integer userId);

}
