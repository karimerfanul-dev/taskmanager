package com.erfandev.taskmanager.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskResponse(
        Long id,
        String title,
        String description,
        Boolean completed,
        LocalDateTime created_at,
        CategoryResponse category
) {
}
