package com.aluracursos.forohub.repository;

import com.aluracursos.forohub.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloIgnoreCaseAndMensajeIgnoreCase(String titulo, String mensaje);
}