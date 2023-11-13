package com.example.building_company.service;

import com.example.building_company.dto.UserDto;
import com.example.building_company.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    UserDto save(User user);

    void delete(UserDto userDto);

    UserDto update(UserDto userDto);
}
