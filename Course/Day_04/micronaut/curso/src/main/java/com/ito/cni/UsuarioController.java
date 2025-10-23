package com.ito.cni;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Status;
import java.util.ArrayList;
import java.util.List;

@Controller("/api/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService = new UsuarioService();
    private List<Usuario> usuarios = usuarioService.generarUsuariosEjemplo();
    
    @Get
    public List<Usuario> obtenerTodos() {
        return usuarios;
    }
    
    @Get("/{id}")
    public Usuario obtenerPorId(@PathVariable String id) {
       return usuarios.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new HttpStatusException(
                HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }

    @Post
    public Usuario crear(@Body Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    @Put("/{id}")
    public Usuario actualizar(@PathVariable String id, 
                             @Body Usuario usuario) {
        Usuario existente = obtenerPorId(id);
        existente.setNombre(usuario.getNombre());
        return existente;
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String id) {
        usuarios.removeIf(u -> u.getId().equals(id));
    }
}