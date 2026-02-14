package br.com.personalfinace.personalfinanceapi.common.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiResponse<T>(
        int status,
        String message,
        T data,
        Map<String, String> errors,
        LocalDateTime timestamp
) {
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(200, message, data, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> created(T data, String message) {
        return new ApiResponse<>(201, message, data, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> error(int status, String message) {
        return new ApiResponse<>(status, message, null, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> validationError(Map<String, String> errors) {
        return new ApiResponse<>(400, "Validation failed", null, errors, LocalDateTime.now());
    }

}

