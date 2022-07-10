package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.UserRegisterRequest;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Transactional
	@Override
	public Integer register(UserRegisterRequest userRegisterRequest) {
		// TODO Auto-generated method stub
		return userMapper.createUser(userRegisterRequest);
	}

	
	@Transactional(readOnly = true)
	@Override
	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(userId);
	}
}
