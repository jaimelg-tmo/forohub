package com.aluracursos.forohub.web.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(
        @NotBlank String username,
        @NotBlank String password
) {}