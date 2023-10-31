package com.example.building_company.service;

import com.example.building_company.exception.UserNotFoundException;
import com.example.building_company.model.User;
import com.example.building_company.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl  implements UserService {
    private final UserRepository userRepository;

    /**
     * Method that allow you to find {@link User} by id.
     *
     * @param id {@link Long} user id.
     * @return {@link User} instance with specified id.
     * @author Nazar Klimovych
     */
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    /**
     * Method that allow you to find {@link User} by email.
     *
     * @param email {@link String} user email.
     * @return {@link User} instance with specified email.
     * @author Nazar Klimovych
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Method that allow you to find all {@link User}'s.
     *
     * @return {@link User} with specified email.
     * @author Nazar Klimovych
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Method for saving {@link User} to a database.
     *
     * @param user {@link User}.
     * @return {@link User} instance.
     * @author Nazar Klimovych
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Method for deleting user from a database.
     *
     * @param id {@link Long} notification id.
     * @return {@link Long} id of deleted user.
     * @author Nazar Klimovych
     */
    @Override
    public Long delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
        userRepository.deleteById(user.getId());
        return id;
    }
}
