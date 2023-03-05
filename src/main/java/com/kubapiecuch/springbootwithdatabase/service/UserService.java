package com.kubapiecuch.springbootwithdatabase.service;

import com.kubapiecuch.springbootwithdatabase.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User updateUserName(Long id, User user);
    User updateEmail(Long id, User user);

    void deleteUser(Long id);
    User getUserById(Long id);

    List<User> getAllUsers();

}
