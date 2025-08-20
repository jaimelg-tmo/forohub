package com.aluracursos.forohub.web;

import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.repository.UsuarioRepository;
import com.aluracursos.forohub.web.dto.UsuarioRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario registrar(@RequestBody @Valid UsuarioRequest request) {
        // Validar si ya existe
        if (usuarioRepository.findByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("Usuario ya existe");
        }

        Usuario usuario = Usuario.builder()
                .username(request.username())
                .password(request.password())
                .build();

        return usuarioRepository.save(usuario);
    }
}
