package com.kubapiecuch.springbootwithdatabase.service;

import com.kubapiecuch.springbootwithdatabase.exception.ResourceNotAvailableException;
import com.kubapiecuch.springbootwithdatabase.model.User;
import com.kubapiecuch.springbootwithdatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new ResourceNotAvailableException("Username is already in use");
        } else if (userRepository.existsByEmail(user.getEmail())){
            throw new ResourceNotAvailableException("Email is already in use");
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            return existingUserOptional.get();
        } else {
            throw new ResourceNotAvailableException("There is no user with provided id: " + id);
        }
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = getUserById(id);
        userRepository.deleteById(existingUser.getId());
    }
}
