package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class UsuarioService {
    private final Map<Long,Usuario> usuarios = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public Usuario crearUsuario(Usuario usuario){
        long id = nextId.incrementAndGet();
        usuario.setId(id);
        usuarios.put(id,usuario);
        return usuario;
    }

    public Usuario getUsuario(Long id){
        return usuarios.get(id);
    }

    public Collection<Usuario> getAllUsuarios(){
        return usuarios.values();
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario){
        if (!usuarios.containsKey(id))
            return null;
        usuario.setId(id);
        usuarios.put(id,usuario);
        return usuario;
    }

    public void eliminarUsuario(Long id){
        usuarios.remove(id);
    }
}
