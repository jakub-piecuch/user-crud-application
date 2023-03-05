package com.kubapiecuch.springbootwithdatabase.service;


import com.kubapiecuch.springbootwithdatabase.model.User;
import com.kubapiecuch.springbootwithdatabase.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp(){
        userService = new UserService(userRepository);
    }

    @Test
    public void testUpdateUserWithInvalidId() {
        Long userId = 6L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        userService.updateUser(userId, user);
    }
}