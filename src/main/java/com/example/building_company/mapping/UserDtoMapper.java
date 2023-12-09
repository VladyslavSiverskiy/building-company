package com.example.building_company.mapping;

import com.example.building_company.dto.UserDto;
import com.example.building_company.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper extends AbstractConverter<User, UserDto> {

    @Override
    protected UserDto convert(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
    }
}
