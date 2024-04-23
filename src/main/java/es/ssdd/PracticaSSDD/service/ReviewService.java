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
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Review crearReview(Review review, Long idPelicula, Long idUsuario){
        if (review.getAutor() == null || review.getContenido() == null)
            return null;
        Pelicula pelicula = peliculaRepository.findById(idPelicula).orElseThrow(() ->
                new IllegalArgumentException("La película con id " + idPelicula + " no existe"));
        Usuario user = usuarioRepository.findById(idUsuario).orElseThrow(() ->
                new IllegalArgumentException("El usuario con id " + idUsuario + " no existe"));
        review.setPelicula(pelicula);
        review.setUsuario(user);
        pelicula.getReviews().add(review);
        user.getReviews().add(review);
        reviewRepository.save(review);
        usuarioRepository.save(user);
        return review;
    }



    public Review getReview(Long id){
        return reviewRepository.findById(id).orElse(null);
    }

    public Collection<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Collection<Review> getAllFilmReviews(Long id) {
        Pelicula pelicula = peliculaRepository.findById(id).get();
        return pelicula.getReviews();
    }

    public Collection<Review> getAllUserReviews(Long id) {
        Usuario user = usuarioRepository.findById(id).get();
        return user.getReviews();
    }

    public Review actualizarReview(Long id, Review review){
        if (!reviewRepository.existsById(id))
            return null;
        if (review.getAutor() == null || review.getContenido() == null)
            return null;
        Review reviewVieja = reviewRepository.findById(id).get();
        review.setId(id);
        review.setPelicula(reviewVieja.getPelicula());
        review.setUsuario(reviewVieja.getUsuario());
        reviewRepository.save(review);
        return review;
    }

    public void eliminarReview(Long id){
        Review review = reviewRepository.findById(id).get();
        Pelicula pelicula = peliculaRepository.findById(review.getPelicula().getId()).get();
        Usuario usuario = usuarioRepository.findById(review.getUsuario().getId()).get();
        usuario.getReviews().remove(review);
        usuarioRepository.save(usuario);
        pelicula.getReviews().remove(review);
        reviewRepository.deleteById(id);
    }
}
