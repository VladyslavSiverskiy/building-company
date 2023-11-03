package com.example.building_company.service;

import com.example.building_company.dto.UserDto;
import com.example.building_company.model.User;

import java.util.List;

public interface UserService {

    /**
     * Method that allow you to find {@link User} by id.
     *
     * @param id {@link Long} user id.
     * @return {@link User} instance with specified id.
     * @author Nazar Klimovych
     */
    UserDto findById(Long id);

    /**
     * Method that allow you to find {@link User} by email.
     *
     * @param email {@link String} user email.
     * @return {@link User} with specified email.
     * @author Nazar Klimovych
     */
    UserDto findByEmail(String email);

    /**
     * Method that allow you to find all {@link User}'s.
     *
     * @return {@link User} with specified email.
     * @author Nazar Klimovych
     */
    List<UserDto> findAll();

    /**
     * Method for saving {@link User} to a database.
     *
     * @param user {@link User}.
     * @return {@link User} instance.
     * @author Nazar Klimovych
     */
    UserDto save(User user);

    /**
     * Method for deleting user from a database.
     *
     * @param id {@link Long} notification id.
     * @return {@link Long} id of deleted user.
     * @author Nazar Klimovych
     */
    Long delete(Long id);
}
