package com.ito.cni.curso;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService = new UsuarioService();
    private List<Usuario> usuarios = usuarioService.generarUsuariosEjemplo();
    
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarios;
    }
    
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable String id) {
        return usuarios.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }
    
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable String id, 
                             @RequestBody Usuario usuario) {
        Usuario existente = obtenerPorId(id);
        existente.setNombre(usuario.getNombre());
        return existente;
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        usuarios.removeIf(u -> u.getId().equals(id));
    }
}