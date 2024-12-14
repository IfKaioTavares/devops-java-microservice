package com.ifba.kaiostavares.user_web.controllers;

import com.ifba.kaiostavares.user_web.dtos.UserRequestDTO;
import com.ifba.kaiostavares.user_web.dtos.UserResponseDTO;
import com.ifba.kaiostavares.user_web.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO requestDTO) {
        var response = userService.createUser(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<UserResponseDTO> getUserByPublicId(@PathVariable String publicId) {
        return ResponseEntity.ok(userService.getUserByPublicId(publicId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<Boolean> deleteUserByPublicId(@PathVariable String publicId) {
        return ResponseEntity.ok(userService.deleteUserByPublicId(publicId));
    }
}
