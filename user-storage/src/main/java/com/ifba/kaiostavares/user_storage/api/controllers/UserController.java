package com.ifba.kaiostavares.user_storage.api.controllers;

import com.ifba.kaiostavares.user_storage.api.dtos.UserRequestDTO;
import com.ifba.kaiostavares.user_storage.api.dtos.UserResponseDTO;
import com.ifba.kaiostavares.user_storage.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usersV1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody  UserRequestDTO createUserRequest){
        var response = userService.createUser(createUserRequest);
        var uri = "/users/" + response.publicId();
        return ResponseEntity.created(URI.create(uri)).body(response);
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<UserResponseDTO> getUserByPublicId(@PathVariable String publicId){
        return ResponseEntity.ok(userService.getUserByPublicId(publicId));
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<Boolean> deleteUserByPublicId(@PathVariable  String publicId){
        return ResponseEntity.ok(userService.deleteUserByPublicId(publicId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
