package com.security.eventify.controller;

import com.security.eventify.dto.userDto.UserDto;
import com.security.eventify.service.EventService;
import com.security.eventify.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> GetAllUsers(){
        List<UserDto> userDtoList = userService.findAllUsers();
        return ResponseEntity.ok(userDtoList);
    }
}
