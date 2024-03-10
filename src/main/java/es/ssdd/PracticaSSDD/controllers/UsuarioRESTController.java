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
        return ResponseEntity.status(201).body(usuarioService.crearUsuario(usuario));
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
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioActualizadao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok().build();
    }
}
