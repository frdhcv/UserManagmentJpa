package org.example.usermanagmentjpa.service;

import org.example.usermanagmentjpa.exception.UserNotFoundException;
import org.example.usermanagmentjpa.model.entity.UserEntity;

import java.sql.SQLException;
import java.util.Optional;
import java.util.List;


public interface UserService {
    UserEntity createUser(UserEntity user) throws SQLException;

    List<UserEntity> getAllUsers() throws SQLException;

    Optional<UserEntity> getUserById(Long id) throws UserNotFoundException;

    void deleteAllUsers() throws SQLException;

    void deleteUserById(Long id) throws UserNotFoundException;

    void updateUserPhoto(Long id, String photoPath) throws SQLException,UserNotFoundException;

    Long getCount() throws SQLException;

    UserEntity updateUser(UserEntity user) throws SQLException;
}
