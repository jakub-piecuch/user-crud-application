package com.kubapiecuch.springbootwithdatabase.service;


import com.kubapiecuch.springbootwithdatabase.dto.UserDto;
import com.kubapiecuch.springbootwithdatabase.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
