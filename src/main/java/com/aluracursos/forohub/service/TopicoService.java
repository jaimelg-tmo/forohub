package com.aluracursos.forohub.service;

import com.aluracursos.forohub.domain.topico.StatusTopico;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.web.dto.TopicoRequest;
import com.aluracursos.forohub.web.dto.TopicoResponse;
import com.aluracursos.forohub.web.dto.TopicoUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository repository;

    @Transactional
    public TopicoResponse crear(@Valid TopicoRequest req) {
        if (repository.existsByTituloIgnoreCaseAndMensajeIgnoreCase(req.titulo(), req.mensaje())) {
            throw new IllegalArgumentException("No puede registrar un tópico duplicado (mismo título y mensaje).");
        }
        Topico topico = Topico.builder()
                .titulo(req.titulo().trim())
                .mensaje(req.mensaje().trim())
                .autor(req.autor().trim())
                .curso(req.curso().trim())
                .status(StatusTopico.ABIERTO)
                .fechaCreacion(LocalDateTime.now())
                .build();
        Topico guardado = repository.save(topico);
        return TopicoResponse.from(guardado);
    }

    @Transactional(readOnly = true)
    public Page<TopicoResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(TopicoResponse::from);
    }

    @Transactional(readOnly = true)
    public TopicoResponse detalle(Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con id " + id));
        return TopicoResponse.from(topico);
    }

    @Transactional
    public TopicoResponse actualizar(Long id, @Valid TopicoUpdateRequest req) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con id " + id));

        // Si actualiza título+mensaje, validar duplicado
        String nuevoTitulo = req.titulo() != null ? req.titulo().trim() : topico.getTitulo();
        String nuevoMensaje = req.mensaje() != null ? req.mensaje().trim() : topico.getMensaje();

        if ((!nuevoTitulo.equalsIgnoreCase(topico.getTitulo()) ||
                !nuevoMensaje.equalsIgnoreCase(topico.getMensaje()))
                && repository.existsByTituloIgnoreCaseAndMensajeIgnoreCase(nuevoTitulo, nuevoMensaje)) {
            throw new IllegalArgumentException("No puede actualizar a un tópico duplicado (mismo título y mensaje).");
        }

        if (req.titulo() != null) topico.setTitulo(nuevoTitulo);
        if (req.mensaje() != null) topico.setMensaje(nuevoMensaje);
        if (req.autor() != null) topico.setAutor(req.autor().trim());
        if (req.curso() != null) topico.setCurso(req.curso().trim());
        if (req.status() != null) topico.setStatus(req.status());

        return TopicoResponse.from(topico);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Tópico no encontrado con id " + id);
        }
        repository.deleteById(id);
    }
}