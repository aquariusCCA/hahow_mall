package com.example.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import com.example.dto.UserLoginRequest;
import com.example.dto.UserRegisterRequest;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Transactional
	@Override
	public Integer register(UserRegisterRequest userRegisterRequest) {
		// 檢查該 email 是否已經註冊過了
		User user = userMapper.getUserByEmail(userRegisterRequest.getEmail());
		
		if(user != null){
			log.warn("該 email {} 已經被註冊", userRegisterRequest.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		// 使用 MD5 生成密碼的雜湊值
		String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
		userRegisterRequest.setPassword(hashedPassword);
		
		// 創建帳號
		return userMapper.createUser(userRegisterRequest);
	}

	
	@Transactional(readOnly = true)
	@Override
	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(userId);
	}


	@Override
	public User login(UserLoginRequest userLoginRequest) {
		User user = userMapper.getUserByEmail(userLoginRequest.getEmail());

		// 檢查 user 是否存在
		if(user == null){
			log.warn("該 Email {} 尚未註冊", userLoginRequest.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		// 使用 MD5 生成密碼的雜湊值
		String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());
		
		
		// 檢查密碼，比較兩者的雜奏值
		if(user.getPassword().equals(hashedPassword)){
			return user;
		}else{
			log.warn("email {} 的密碼不正確", userLoginRequest.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
}
