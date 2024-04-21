package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Pelicula;
import es.ssdd.PracticaSSDD.entities.Review;
import es.ssdd.PracticaSSDD.repositories.PeliculaRepository;
import es.ssdd.PracticaSSDD.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;

    public Review crearReview(Review review, Long idPelicula){
        if (review.getAutor() == null || review.getContenido() == null)
            return null;
        Pelicula pelicula = peliculaRepository.findById(idPelicula).orElseThrow(() ->
                new IllegalArgumentException("La película con id " + idPelicula + " no existe"));
        reviewRepository.save(review);
        review.setPelicula(pelicula);
        pelicula.getReviews().add(review);
        return review;
    }

    public Review getReview(Long id){
        return reviewRepository.findById(id).orElse(null);
    }

    public Collection<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Review actualizarReview(Long id, Review review){
        if (!reviewRepository.existsById(id))
            return null;
        if (review.getAutor() == null || review.getContenido() == null)
            return null;
        Review reviewVieja = reviewRepository.findById(id).get();
        Pelicula pelicula = peliculaRepository.findById(reviewVieja.getPelicula().getId()).get();
        pelicula.getReviews().remove(reviewVieja);
        review.setId(id);
        reviewRepository.save(review);
        pelicula.getReviews().add(review);
        return review;
    }

    public void eliminarReview(Long id){
        Review review = reviewRepository.findById(id).get();
        Pelicula pelicula = peliculaRepository.findById(review.getPelicula().getId()).get();
        pelicula.getReviews().remove(review);
        reviewRepository.deleteById(id);
    }
}
