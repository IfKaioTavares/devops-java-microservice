package com.ifba.kaiostavares.user_web.services;

import com.ifba.kaiostavares.user_web.dtos.UserRequestDTO;
import com.ifba.kaiostavares.user_web.dtos.UserResponseDTO;
import com.ifba.kaiostavares.user_web.exceptions.RestErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class UserService {
    private final WebClient webClient;

    public UserService(WebClient webClient) {
        this.webClient = webClient;
    }

    private <T> Mono<T> handleErrors(Mono<T> response) {
        return response.onErrorResume(WebClientResponseException.class, e -> {
            if (e.getStatusCode().is4xxClientError() || e.getStatusCode().is5xxServerError()) {
                return Mono.error(new RestErrorException(
                        e.getStatusCode(),
                        e.getResponseBodyAsString()
                ));
            }
            return Mono.error(e);
        });
    }

    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        return handleErrors(webClient.post()
                .uri("/usersV1")
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(UserResponseDTO.class))
                .block();
    }

    public UserResponseDTO getUserByPublicId(String publicId) {
        return handleErrors(webClient.get()
                .uri("/usersV1/{publicId}", publicId)
                .retrieve()
                .bodyToMono(UserResponseDTO.class))
                .block();
    }

    public List<UserResponseDTO> getAllUsers() {
        return handleErrors(webClient.get()
                .uri("/usersV1/all")
                .retrieve()
                .bodyToFlux(UserResponseDTO.class)
                .collectList())
                .block();
    }

    public boolean deleteUserByPublicId(String publicId) {
        if (publicId == null || publicId.isBlank()) {
            throw new RestErrorException(HttpStatus.BAD_REQUEST, "Public ID cannot be null or blank");
        }

        return handleErrors(webClient.delete()
                .uri("/usersV1/{publicId}", publicId)
                .retrieve()
                .bodyToMono(Boolean.class))
                .block();
    }
}