package com.aluracursos.forohub.web.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicoRequest(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotBlank String autor,
        @NotBlank String curso
) {}