package org.example.usermanagmentjpa.service;

import org.example.usermanagmentjpa.exception.UserNotFoundException;
import org.example.usermanagmentjpa.repository.UserRepository;
import org.example.usermanagmentjpa.model.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteAllUsers() throws SQLException {
        userRepository.deleteAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUserPhoto(Long id, String photoPath) throws SQLException, UserNotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        user.setPhotoPath(photoPath);
        userRepository.save(user);
    }

    @Override
    public Long getCount() throws SQLException {
        return userRepository.count();
    }

    @Override
    public UserEntity updateUser(UserEntity user) throws SQLException {
        Optional<UserEntity> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            userEntity.setUsername(user.getUsername());
            userEntity.setPassword(user.getPassword());
            userEntity.setId(user.getId());
            userEntity.setCreatedAt(user.getCreatedAt());
            userEntity.setUpdatedAt(user.getUpdatedAt());
            userRepository.save(userEntity);
        }
        return user;
    }
}
