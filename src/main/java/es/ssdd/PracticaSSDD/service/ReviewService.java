package es.ssdd.PracticaSSDD.service;

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
        reviewRepository.save(review);
        peliculaRepository.getReferenceById(idPelicula).getReviews().add(review);
        return review;
    }

    public Review getReview(Long id){
        return reviewRepository.findById(id).orElse(null);
    }

    public Collection<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Review actualizarReview(Long id, Review review, Long idPelicula){
        if (!reviewRepository.existsById(id))
            return null;
        if (review.getAutor() == null || review.getContenido() == null)
            return null;
        review.setId(id);
        reviewRepository.save(review);
        peliculaRepository.getReferenceById(idPelicula).getReviews().add(review);
        return review;
    }

    public void eliminarReview(Long id){
        reviewRepository.deleteById(id);
    }
}
