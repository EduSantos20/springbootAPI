package com.example.springbootAPI.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// NotBlank não podemos temos nome em branco
// NotNull não podemos ter valores null
public record ProductRecordDto(@NotBlank String name, @NotNull BigDecimal value) {

}
