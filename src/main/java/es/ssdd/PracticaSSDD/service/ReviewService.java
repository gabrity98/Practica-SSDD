package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Review;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class ReviewService {
    private final Map<Long, Review> reviews = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public Review crearReview(Review review){
        if (review.getAutor() == null || review.getContenido() == null)
            return null;
        long id = nextId.incrementAndGet();
        review.setId(id);
        reviews.put(id,review);
        return review;
    }

    public Review getReview(Long id){
        return reviews.get(id);
    }

    public Collection<Review> getAllReviews(){
        return reviews.values();
    }

    public Review actualizarReview(Long id, Review review){
        if (!reviews.containsKey(id))
            return null;
        if (review.getAutor() == null || review.getContenido() == null)
            return null;
        review.setId(id);
        reviews.put(id, review);
        return review;
    }

    public void eliminarReview(Long id){
        reviews.remove(id);
    }
}
