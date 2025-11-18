package com.security.eventify.service;

import com.security.eventify.dto.userDto.UserDto;
import com.security.eventify.dto.userDto.UserRegisterDto;
import com.security.eventify.mapper.UserMapper;
import com.security.eventify.model.User;
import com.security.eventify.repository.UserRepository;
import com.security.eventify.service.interfaces.UserInterface;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserInterface {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto registerUser(UserRegisterDto userRegisterDto){
        User user = userMapper.userRegisterDtoToUser(userRegisterDto);
        String hash = passwordEncoder.encode(userRegisterDto.getPassword());
        String role = "ROLE_"+ userRegisterDto.getRole();
        user.setRole(role);
        user.setPassword(hash);
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }
}
