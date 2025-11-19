package com.security.eventify.service;

import com.security.eventify.dto.userDto.UserDto;
import com.security.eventify.dto.userDto.UserRegisterDto;
import com.security.eventify.exception.UserAlreadyExists;
import com.security.eventify.exception.UserNotFound;
import com.security.eventify.mapper.UserMapper;
import com.security.eventify.model.User;
import com.security.eventify.repository.UserRepository;
import com.security.eventify.service.interfaces.UserInterface;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.text.html.Option;
import java.util.Optional;

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
        Optional<User> isUserExists = userRepository.findByEmail(user.getEmail());
        if(isUserExists.isPresent()){
            throw new UserAlreadyExists("On peut pas cree un autre utilisateur avec cette email il est deja cree");
        }else{
            String hash = passwordEncoder.encode(userRegisterDto.getPassword());
            String role = "ROLE_"+ userRegisterDto.getRole();
            user.setRole(role);
            user.setPassword(hash);
            userRepository.save(user);
            return userMapper.userToUserDto(user);
        }
    }
    @Override
    public UserDto findByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFound("utilisateur n est pas trouver avec ce id "));
        return userMapper.userToUserDto(user);
    }
    @Override
    public UserDto updateUser(int id , String role){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User userExsistant = user.get();
            userExsistant.setRole("ROLE_"+role);
            User updatedUser = userRepository.save(userExsistant);
            return userMapper.userToUserDto(updatedUser);
        }else{
            throw new UserNotFound("utilisateur avec cette pas trouver !");
        }
    }
}
