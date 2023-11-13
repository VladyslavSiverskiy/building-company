package com.example.building_company.service;

import com.example.building_company.constants.ExceptionMessages;
import com.example.building_company.dto.UserDto;
import com.example.building_company.exception.UserNotFoundException;
import com.example.building_company.model.User;
import com.example.building_company.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl  implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<User> findByUsername(String username) {
        if (username != null && !username.trim().isEmpty()) {
            return userRepository.findByUsername(username);
        }
        throw new NullPointerException(ExceptionMessages.EMAIL_CANT_BE_NULL);
    }

    @Override
    public UserDto save(User user) {
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException(ExceptionMessages.OBJECT_CANT_BE_NULL);
        }
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void delete(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
            .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));
        userRepository.delete(user);
    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }
}
