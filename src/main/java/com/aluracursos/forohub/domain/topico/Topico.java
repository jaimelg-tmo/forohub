package com.aluracursos.forohub.domain.topico;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "topico",
        uniqueConstraints = @UniqueConstraint(name = "uk_topico_titulo_mensaje", columnNames = {"titulo", "mensaje"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @Column(nullable = false, length = 120)
    private String autor;

    @Column(nullable = false, length = 120)
    private String curso;
}