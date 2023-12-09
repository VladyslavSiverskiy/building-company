package com.example.building_company.mapping;

import com.example.building_company.dto.UserDto;
import com.example.building_company.model.User;
import org.modelmapper.AbstractConverter;

public class UserMapper extends AbstractConverter<UserDto, User> {

    @Override
    protected User convert(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .build();
    }
}
