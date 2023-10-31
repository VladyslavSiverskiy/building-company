package com.example.building_company.repository;

import com.example.building_company.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Method for getting user by email.
     *
     * @param email {@link String} user email.
     * @return {@link User} instance.
     * @author Nazar Klimovych
     */
    User findByEmail(String email);
}
