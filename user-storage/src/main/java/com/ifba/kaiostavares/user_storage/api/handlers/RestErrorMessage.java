package com.ifba.kaiostavares.user_storage.api.handlers;

import org.springframework.http.HttpStatus;

public record RestErrorMessage(
        HttpStatus status,
        String message
) {
}