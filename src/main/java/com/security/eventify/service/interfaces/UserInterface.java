package com.security.eventify.service.interfaces;

import com.security.eventify.dto.userDto.UserDto;
import com.security.eventify.dto.userDto.UserRegisterDto;
import com.security.eventify.model.User;

public interface UserInterface {
    UserDto registerUser(UserRegisterDto user);
    UserDto findByEmail(String email);
    UserDto updateUser(int id , String role);
    void deleteUser(int id);
}
