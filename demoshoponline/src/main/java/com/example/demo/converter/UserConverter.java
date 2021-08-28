package com.example.demo.converter;

import org.modelmapper.ModelMapper;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

public class UserConverter {

	public UserDto modelToDto(User user) {
		ModelMapper mapper =new ModelMapper();
		UserDto map = mapper.map(user, UserDto.class);
		return map ;
	}
	
	public User dtoToModel(UserDto userDto) {
		ModelMapper mapper = new ModelMapper();
		User map = mapper.map(userDto, User.class);
		return map;
	}
}
