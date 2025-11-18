package com.security.eventify.controller;

import com.security.eventify.dto.userDto.UserDto;
import com.security.eventify.dto.userDto.UserRegisterDto;
import com.security.eventify.model.User;
import com.security.eventify.service.UserService;
import com.security.eventify.service.interfaces.UserInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserInterface userService;
    public UserController(UserInterface userService) {
        this.userService = userService;
    }
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserRegisterDto userRegisterDto){
        UserDto userDto = userService.registerUser(userRegisterDto);
        return ResponseEntity.ok(userDto);
    }

}
