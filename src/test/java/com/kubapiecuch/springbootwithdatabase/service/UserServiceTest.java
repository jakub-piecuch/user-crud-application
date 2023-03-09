package com.kubapiecuch.springbootwithdatabase.service;


import com.kubapiecuch.springbootwithdatabase.dto.UserDto;
import com.kubapiecuch.springbootwithdatabase.exception.ResourceNotAvailableException;
import com.kubapiecuch.springbootwithdatabase.model.User;
import com.kubapiecuch.springbootwithdatabase.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDtoMapper userDtoMapper;
    @InjectMocks
    private UserService userService;

//    @BeforeEach
//    public void setUp(){
//        userService = new UserService(userRepository, userDtoMapper);
//    }

    @Test
    public void expectedExceptionInUpdateUserWithInvalidId() {
        Long userId = 4L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        Assertions
                .assertThrows(
                        ResourceNotAvailableException.class,
                        () -> userService.updateUser(userId, user)
                );
    }

    @Test
    public void createUserTest() {
        //Given
        User user = new User(
                1L,
                "johndoe",
                "1234",
                "johndoe@gmail.com"
        );

        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        //When
        User createdUser = userService.createUser(user);

        //Then
        Assertions.assertEquals(createdUser, user);
    }

    @Test
    public void shouldGetUserWithValidId() {
        Long userId = 1L;
        //Given
        User user = new User(1L, "Jhon", "124", "john@mail.com");
        UserDto expectedUserDto = new UserDto(1L, "Jhon", "john@mail.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userDtoMapper.apply(user)).thenReturn(expectedUserDto);

        //When
        UserDto actualUserDto = userService.getUserById(userId);

        //Then
        Assertions.assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    public void shouldUpdateUserWithValidId() {

        Long id = 1L;
        User existingUser = new User(id, "john.doe", "123", "john.doe@gmail.com");
        User updatedUser = new User(id, "jane.doe", "123", "jane.doe@gmail.com");
        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        // Act
        User result = userService.updateUser(id, updatedUser);

        // Assert
        Assertions.assertEquals(updatedUser.getUsername(), result.getUsername());
        Assertions.assertEquals(updatedUser.getEmail(), result.getEmail());
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    public void shouldDeleteUserWithValidId() {

        //Given
        Long userId = 1L;
        User user = new User(userId, "john.doe", "123", "john.doe@gmail.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        //When
        userService.deleteUser(userId);

        //Then
//        verify(userRepository, times(1)).deleteById(userId);
        verify(userRepository).deleteById(userId);
    }
}
