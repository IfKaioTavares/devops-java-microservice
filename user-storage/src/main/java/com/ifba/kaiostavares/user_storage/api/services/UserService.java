package com.ifba.kaiostavares.user_storage.api.services;

import com.ifba.kaiostavares.user_storage.api.dtos.UserRequestDTO;
import com.ifba.kaiostavares.user_storage.api.dtos.UserResponseDTO;
import com.ifba.kaiostavares.user_storage.api.exceptions.ContentNotFound;
import com.ifba.kaiostavares.user_storage.api.exceptions.EmailAlreadyInUseException;
import com.ifba.kaiostavares.user_storage.model.User;
import com.ifba.kaiostavares.user_storage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO newUserRequest){
        if(userRepository.findByEmail(newUserRequest.email()).isPresent()){
            throw new EmailAlreadyInUseException("Email already in use");
        }
        var newUser = new User(newUserRequest.username(), newUserRequest.email());
        userRepository.save(newUser);
        return new UserResponseDTO(newUser.getUsername(), newUser.getEmail(), newUser.getPublicId());
    }

    public List<UserResponseDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDTO(user.getUsername(), user.getEmail(), user.getPublicId()))
                .toList();
    }

    public UserResponseDTO getUserByPublicId(String publicId){
        var user = userRepository.findByPublicId(UUID.fromString(publicId))
                .orElseThrow(() -> new ContentNotFound("User not found"));
        return new UserResponseDTO(user.getUsername(), user.getEmail(), user.getPublicId());
    }

    public boolean deleteUserByPublicId(String publicId){
        var user = userRepository.findByPublicId(UUID.fromString(publicId))
                .orElseThrow(() -> new ContentNotFound("User not found"));
        userRepository.delete(user);
        return true;
    }
}
