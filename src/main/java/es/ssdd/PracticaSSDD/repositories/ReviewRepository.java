package es.ssdd.PracticaSSDD.repositories;

import es.ssdd.PracticaSSDD.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
