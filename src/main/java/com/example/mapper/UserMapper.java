package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.UserRegisterRequest;
import com.example.model.User;

@Mapper
public interface UserMapper {

	Integer createUser(UserRegisterRequest userRegisterRequest);

	User getUserById(Integer userId);

}
