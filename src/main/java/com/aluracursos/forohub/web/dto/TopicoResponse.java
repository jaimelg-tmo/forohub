package com.aluracursos.forohub.web.dto;

import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.topico.StatusTopico;

import java.time.LocalDateTime;

public record TopicoResponse(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        String autor,
        String curso
) {
    public static TopicoResponse from(Topico t) {
        return new TopicoResponse(
                t.getId(),
                t.getTitulo(),
                t.getMensaje(),
                t.getFechaCreacion(),
                t.getStatus(),
                t.getAutor(),
                t.getCurso()
        );
    }
}