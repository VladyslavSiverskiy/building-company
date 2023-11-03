package com.example.building_company.mapping;

import com.example.building_company.dto.UserDto;
import com.example.building_company.model.User;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for mapping between {@link User} entities and {@link UserDto}.
 */
@Component
public class UserDtoMapper {

    /**
     * Converts a {@link User} entity to a {@link UserDto}.
     *
     * @param user The {@link User} entity to be converted.
     * @return A {@link UserDto} representing the provided entity.
     * @author Nazar Klimovych
     */
    public UserDto convertToDto(User user) {
        return new UserDto(
                user.getEmail(),
                user.getPassword(),
                user.getUsername());
    }

    /**
     * Converts a {@link UserDto} entity to a {@link User}.
     *
     * @param userDto The {@link UserDto} entity to be converted.
     * @return A {@link User} representing the provided entity.
     * @author Nazar Klimovych
     */
    public User convertToEntity(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .build();
    }
}
