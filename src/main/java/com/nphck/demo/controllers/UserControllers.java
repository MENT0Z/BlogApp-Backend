package com.nphck.demo.controllers;

import com.nphck.demo.payloads.ApiResponse;
import com.nphck.demo.payloads.UserDTO;
import com.nphck.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControllers {
    @Autowired
    private UserService userService;

    //GET
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>>getallusers()
    {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }


    //POST
    @PostMapping("/")
    public ResponseEntity<UserDTO>createUser(@Valid @RequestBody UserDTO userDTO){
    UserDTO createUserDTo = this.userService.createUser(userDTO);
    return new ResponseEntity<>(createUserDTo, HttpStatus.CREATED);
    }

    //PUT
   @PutMapping("/{userId}")
    public ResponseEntity<UserDTO>updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable("userId")Integer uid){
    UserDTO updateusr = this.userService.updateUser(userDTO,uid);
    return ResponseEntity.ok(updateusr);
   }
    //DELETE
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>deleteuser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user Deleted Sucessfully",true),HttpStatus.OK);

    }
    // get single user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO>getuser(@PathVariable Integer userId){
        UserDTO userDTO = this.userService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    // delete all users
    @DeleteMapping("/")
    public ResponseEntity<ApiResponse>deleteAllUsers(){
        this.userService.deleteAllUsers();
        return new ResponseEntity<ApiResponse>(new ApiResponse("All Users Deleted Sucessfully",true),HttpStatus.OK);
    }

}
