package com.example.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserRegisterRequest {
	
	private Integer userId;

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Pattern(regexp = "^[\\w]{6,12}$", message = "密碼只能是數字、大小寫字母、下滑線，並且長度為 6 ~ 12 位")
	private String password;

	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRegisterRequest [email=" + email + ", password=" + password + "]";
	}
}
