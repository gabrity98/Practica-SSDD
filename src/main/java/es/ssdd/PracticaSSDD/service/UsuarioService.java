package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Pelicula;
import es.ssdd.PracticaSSDD.entities.Review;
import es.ssdd.PracticaSSDD.entities.Usuario;
import es.ssdd.PracticaSSDD.repositories.PeliculaRepository;
import es.ssdd.PracticaSSDD.repositories.ReviewRepository;
import es.ssdd.PracticaSSDD.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;

    public Usuario crearUsuario(Usuario usuario){
        if (usuario.getNombre() == null || usuario.getEmail() == null)
            return null;
        usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario getUsuario(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Collection<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioNew){
        Usuario user = usuarioRepository.findById(id).orElse(null);
        if (user == null)
            return null;
        if (usuarioNew.getNombre() == null || usuarioNew.getEmail() == null)
            return null;
        usuarioNew.setPeliculas(user.getPeliculas());
        usuarioNew.setId(id);
        usuarioRepository.save(usuarioNew);
        return usuarioNew;
    }

    public void eliminarUsuario(Long id){
        Usuario user = usuarioRepository.getById(id);
        for(Review review : user.getReviews()){
            Pelicula pelicula = peliculaRepository.findById(review.getPelicula().getId()).get();
            user.getReviews().remove(review);
            pelicula.getReviews().remove(review);
            reviewRepository.delete(review);
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario comprobarUsuario(String nombre, String email){
        for (Usuario usuarioAux: usuarioRepository.findAll()) {
            if (usuarioAux.getNombre().equals(nombre) && usuarioAux.getEmail().equals(email)) {
                return usuarioAux;
            }
        }

        return null;
    }
}
