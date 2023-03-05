package com.kubapiecuch.springbootwithdatabase.service;

import com.kubapiecuch.springbootwithdatabase.dto.UserDto;
import com.kubapiecuch.springbootwithdatabase.exception.ResourceNotAvailableException;
import com.kubapiecuch.springbootwithdatabase.model.User;
import com.kubapiecuch.springbootwithdatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void checkIfUserNameExistsInDb(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new ResourceNotAvailableException("Username is already in use");
        }
    }

    private void checkIfEmailExistsInDb(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ResourceNotAvailableException("Email is already in use");
        }
    }


    public User createUser(User user) {
        checkIfUserNameExistsInDb(user.getUsername());
        checkIfEmailExistsInDb(user.getEmail());
        return userRepository.save(user);
    }
    
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getUsername(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            return existingUserOptional.get();
        } else {
            throw new ResourceNotAvailableException("There is no user with provided id: " + id);
        }
    }

    public User updateUserName(Long id, User user) {
        checkIfUserNameExistsInDb(user.getUsername());
        User existingUser = getUserById(id);
        existingUser.setUsername(user.getUsername());
        return userRepository.save(existingUser);
    }

    public User updateEmail(Long id, User user) {
        checkIfEmailExistsInDb(user.getEmail());
        User existingUser = getUserById(id);
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User existingUser = getUserById(id);
        userRepository.deleteById(existingUser.getId());
    }
}
