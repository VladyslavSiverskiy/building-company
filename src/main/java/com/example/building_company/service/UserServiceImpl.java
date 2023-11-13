package com.example.building_company.service;

import com.example.building_company.constants.ExceptionMessages;
import com.example.building_company.dto.UserDto;
import com.example.building_company.exception.UserNotFoundException;
import com.example.building_company.model.User;
import com.example.building_company.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username != null && !username.trim().isEmpty()) {
            return userRepository.findByUsername(username).get();
        }
        throw new NullPointerException(ExceptionMessages.EMAIL_CANT_BE_NULL);
    }
}
