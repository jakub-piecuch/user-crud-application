package com.kubapiecuch.springbootwithdatabase.user;

import com.kubapiecuch.springbootwithdatabase.exception.ResourceNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserDtoMapper userDtoMapper
    ) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
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
                .map(userDtoMapper)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id){
        return userRepository.findById(id)
                .map(userDtoMapper)
                .orElseThrow(() -> new ResourceNotAvailableException(
                        "user with id [%s] not found".formatted(id)
                ));
    }

    public User updateUser(Long id, User user) {
        checkIfUserNameExistsInDb(user.getUsername());
        checkIfEmailExistsInDb(user.getEmail());
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotAvailableException(
                        "user with id [%s] not found".formatted(id)
                ));
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotAvailableException(
                        "user with id [%s] not found".formatted(id)
                ));
        userRepository.deleteById(existingUser.getId());
    }


}
