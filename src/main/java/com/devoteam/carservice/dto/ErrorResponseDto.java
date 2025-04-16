package com.devoteam.carservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Default API error response")
public record ErrorResponseDto(@Schema(description = "Code error", example = "500") int status,
                               @Schema(description = "Message error") String message,
                               @Schema(description = "Validators error") List<String> errors) {
}
