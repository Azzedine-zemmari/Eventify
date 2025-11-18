package com.security.eventify.mapper;

import com.security.eventify.dto.userDto.UserDto;
import com.security.eventify.dto.userDto.UserRegisterDto;
import com.security.eventify.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);  // pour retourner un utilisateur sans mot de pass
    @Mapping(target = "password", ignore = true) // ignorer le mot de pass pour saisir au service
    @Mapping(target="id" , ignore = true)
    User userRegisterDtoToUser(UserRegisterDto userDto);
}
