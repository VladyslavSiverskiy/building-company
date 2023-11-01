package com.example.building_company.dto;

import com.example.building_company.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String username;

    public static User toEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .build();
    }

    public static UserDto fromEntity(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getUsername());
    }
}
