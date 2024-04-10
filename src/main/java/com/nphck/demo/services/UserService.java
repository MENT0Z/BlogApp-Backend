package com.nphck.demo.services;

import com.nphck.demo.payloads.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user,Integer uid);
    UserDTO getUserById(Integer uid);
    List<UserDTO> getAllUsers();
    void deleteUser(Integer Uid);
    void deleteAllUsers();

}
