package com.aluracursos.forohub.web.dto;

import com.aluracursos.forohub.domain.topico.StatusTopico;
import jakarta.validation.constraints.Size;

public record TopicoUpdateRequest(
        @Size(min = 1, max = 200) String titulo,
        String mensaje,
        String autor,
        String curso,
        StatusTopico status
) {}