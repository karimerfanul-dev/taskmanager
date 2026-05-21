package com.erfandev.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequest(
        @NotBlank( message = "title can't be blank")
        @Size(min=3,max = 100,message = "title must be between 3 to 100 length")
        String title,
        @Size(max = 500, message = "Description can't be greater than 500 length")
        String description,
        Boolean completed

) {
}
