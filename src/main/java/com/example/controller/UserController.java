package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserLoginRequest;
import com.example.dto.UserRegisterRequest;
import com.example.model.User;
import com.example.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	// 因為要保護資料，所以我們選擇 POST
	@PostMapping("/users/login")
	public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
		User user = userService.login(userLoginRequest);

		return ResponseEntity.status(HttpStatus.OK).body(user);		
	}
	
	
	/*
	register 方法選擇 POST 的兩個理由
	1. RESTful 中，創建資源對應到 POST 方法
	2. 資安考量，需要使用 request body 傳遞參數
	email 和 密碼這種隱私資訊，要用 request body 傳遞
	 不能使用 url 參數傳遞，會洩漏資訊
	 */
	@PostMapping("/users/register")
	public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
		
		// 註冊
		userService.register(userRegisterRequest);
		
		// 獲取剛註冊的用戶編號
		Integer userId = userRegisterRequest.getUserId();

		// 將剛註冊的用戶給找出來
		User user = userService.getUserById(userId);

		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
}
