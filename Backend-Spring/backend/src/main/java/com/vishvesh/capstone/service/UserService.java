package com.vishvesh.capstone.service;

import com.vishvesh.capstone.dto.UserDto;
import com.vishvesh.capstone.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
