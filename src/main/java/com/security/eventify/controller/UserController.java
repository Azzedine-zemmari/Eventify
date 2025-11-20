package com.security.eventify.controller;

import com.security.eventify.dto.userDto.RegistrationDto;
import com.security.eventify.dto.userDto.UserDto;
import com.security.eventify.dto.userDto.UserRegisterDto;
import com.security.eventify.model.User;
import com.security.eventify.service.UserService;
import com.security.eventify.service.interfaces.RegistrationInterface;
import com.security.eventify.service.interfaces.UserInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserInterface userService;
    private final RegistrationInterface registrationService;
    public UserController(UserInterface userService,RegistrationInterface registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserRegisterDto userRegisterDto){
        UserDto userDto = userService.registerUser(userRegisterDto);
        return ResponseEntity.ok(userDto);
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(Authentication authentication){
       String email = authentication.getName();
       UserDto userDto = userService.findByEmail(email);
       String name = userDto.getName();
       String role = userDto.getRole();

       return ResponseEntity.ok("email : " + email + " name : " + name + " role : " + role);
    }
    @PutMapping("/update/user/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") int id , @RequestBody String role){
        UserDto userDto = userService.updateUser(id , role);
        return ResponseEntity.ok(userDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }

//    register a un evenement
    @PostMapping("/user/events/{id}/register")
    public ResponseEntity<RegistrationDto> registerEvent(@PathVariable("id") int id , Authentication authentication ){
        String username = authentication.getName();
        RegistrationDto registrationDto1 = registrationService.registrer(id,username);
        return ResponseEntity.ok(registrationDto1);
    }
    @GetMapping("/user/registrations")
    public ResponseEntity<List<RegistrationDto>> userRegistration(Authentication authentication){
        String username = authentication.getName();
        List<RegistrationDto> registrationDtoList = registrationService.getAllRegistration(username);
        return ResponseEntity.ok(registrationDtoList);
    }

}
