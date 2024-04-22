package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Pelicula;
import es.ssdd.PracticaSSDD.entities.Review;
import es.ssdd.PracticaSSDD.entities.Usuario;
import es.ssdd.PracticaSSDD.repositories.PeliculaRepository;
import es.ssdd.PracticaSSDD.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class PeliculaService {
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Pelicula crearPelicula(Pelicula pelicula, Long userID){
        if (pelicula.getNombre() == null || pelicula.getDirector() == null || pelicula.getGenero() == null || pelicula.getPuntuacion() == null)
            return null;
        Usuario user = usuarioRepository.getById(userID);
        pelicula.getUsuarios().add(user);
        user.getPeliculas().add(pelicula);
        peliculaRepository.save(pelicula);
        usuarioRepository.save(user);
        return pelicula;
    }

    public Pelicula getPelicula(Long id){
        return peliculaRepository.findById(id).orElse(null);
    }

    public Collection<Pelicula> getAllPeliculas(){
        return peliculaRepository.findAll();
    }

    public Collection<Pelicula> getAllUserPeliculas(Long id){
        Usuario user = usuarioRepository.findById(id).get();
        return user.getPeliculas();
    }

    public Pelicula actualizarPelicula(Long id, Pelicula pelicula){
        if (!peliculaRepository.existsById(id))
            return null;
        if (pelicula.getNombre() == null || pelicula.getDirector() == null || pelicula.getGenero() == null || pelicula.getPuntuacion() == null)
            return null;
        pelicula.setId(id);
        peliculaRepository.save(pelicula);
        return pelicula;
    }

    public void eliminarPelicula(Long id){
        Pelicula pelicualAux = peliculaRepository.getById(id);
        for(Usuario user : pelicualAux.getUsuarios()){
            user.getPeliculas().remove(pelicualAux);
            usuarioRepository.save(user);
        }
        for(Review review : pelicualAux.getReviews()){
            Usuario user = review.getUsuario();
            user.getReviews().remove(review);
            usuarioRepository.save(user);
        }
        peliculaRepository.deleteById(id);
    }
}
