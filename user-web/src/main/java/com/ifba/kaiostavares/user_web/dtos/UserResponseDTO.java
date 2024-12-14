package com.ifba.kaiostavares.user_web.dtos;

import java.util.UUID;

public record UserResponseDTO(
        String username,
        String email,
        UUID publicId
) {
}
