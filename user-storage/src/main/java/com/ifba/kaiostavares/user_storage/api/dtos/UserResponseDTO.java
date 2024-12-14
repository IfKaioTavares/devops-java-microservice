package com.ifba.kaiostavares.user_storage.api.dtos;
import java.util.UUID;


public record UserResponseDTO(
        String username,
        String email,
        UUID publicId
) {
}
