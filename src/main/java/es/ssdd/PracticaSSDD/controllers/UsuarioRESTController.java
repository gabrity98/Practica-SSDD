package es.ssdd.PracticaSSDD.controllers;

import es.ssdd.PracticaSSDD.entities.Usuario;
import es.ssdd.PracticaSSDD.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRESTController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }

        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);

        if (nuevoUsuario == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(nuevoUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.getUsuario(id);
        if (usuario == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<Collection<Usuario>> getAllUsuario(){
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuarioActualizadao = usuarioService.actualizarUsuario(id, usuario);
        if (usuarioActualizadao == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usuarioActualizadao);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> actualizarParcialmenteUsuario(@PathVariable Long id, @RequestBody Usuario parcialUsuario) {
        Usuario existente = usuarioService.getUsuario(id);
        if (parcialUsuario.getNombre() != null) {
            existente.setNombre(parcialUsuario.getNombre());
        }
        if (parcialUsuario.getEmail() != null) {
            existente.setEmail(parcialUsuario.getEmail());
        }
        usuarioService.actualizarUsuario(id, existente);
        return ResponseEntity.ok(existente);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok().build();
    }
}
