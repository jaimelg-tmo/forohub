package com.aluracursos.forohub.web;

import com.aluracursos.forohub.service.TopicoService;
import com.aluracursos.forohub.web.dto.TopicoRequest;
import com.aluracursos.forohub.web.dto.TopicoResponse;
import com.aluracursos.forohub.web.dto.TopicoUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService service;

    // 1. Crear tópico
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopicoResponse crear(@RequestBody @Valid TopicoRequest request) {
        return service.crear(request);
    }

    // 2. Listar tópicos (paginado/orden configurable)
    @GetMapping
    public Page<TopicoResponse> listar(@PageableDefault(size = 10, sort = "fechaCreacion") Pageable pageable) {
        return service.listar(pageable);
    }

    // 3. Detalle de un tópico
    @GetMapping("/{id}")
    public TopicoResponse detalle(@PathVariable Long id) {
        return service.detalle(id);
    }

    // 4. Actualizar tópico
    @PutMapping("/{id}")
    public TopicoResponse actualizar(@PathVariable Long id, @RequestBody @Valid TopicoUpdateRequest request) {
        return service.actualizar(id, request);
    }

    // 5. Eliminar tópico
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}