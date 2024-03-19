package es.ssdd.PracticaSSDD.controllers;

import es.ssdd.PracticaSSDD.entities.Review;
import es.ssdd.PracticaSSDD.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/reviews")
public class ReviewRESTController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> crearReview(@RequestBody Review review){
        return ResponseEntity.status(201).body(reviewService.crearReview(review));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id){
        Review review = reviewService.getReview(id);
        if (review == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(review);
    }

    @GetMapping
    public ResponseEntity<Collection<Review>> getAllReview(){
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> actualizarReview(@PathVariable Long id, @RequestBody Review review){
        Review reviewActualizadao = reviewService.actualizarReview(id, review);
        if (reviewActualizadao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewActualizadao);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Review> actualizarParcialmenteReview(@PathVariable Long id, @RequestBody Review parcialReview) {
        Review existente = reviewService.getReview(id);
        if (parcialReview.getAutor() != null) {
            existente.setAutor(parcialReview.getAutor());
        }
        if (parcialReview.getContenido() != null) {
            existente.setContenido(parcialReview.getContenido());
        }
        if (parcialReview.getPuntuacion() != 0.0) {
            existente.setPuntuacion(parcialReview.getPuntuacion());
        }
        reviewService.actualizarReview(id, existente);
        return ResponseEntity.ok(existente);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReview(@PathVariable Long id){
        reviewService.eliminarReview(id);
        return ResponseEntity.ok().build();
    }
}
