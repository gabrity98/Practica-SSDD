package es.ssdd.PracticaSSDD.controllers;

import es.ssdd.PracticaSSDD.entities.Review;
import es.ssdd.PracticaSSDD.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public String listarReviews(Model model){
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviews";
    }

    @GetMapping("/review/agregar")
    public String mostrarFormularioReviewAgregar(Model model){
        model.addAttribute("reviews", new Review());
        return "agregar-review";
    }

    @PostMapping("/review/agregar")
    public String agregarReview(Review review){
        reviewService.crearReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/review/editar/{id}")
    public String mostrarFormularioReviewEditar(@PathVariable Long id, Model model){
        Review review = reviewService.getReview(id);
        if (review == null)
            return "redirect:/reviews";
        model.addAttribute("review",review);
        return "editar-review";
    }

    @PostMapping("/review/editar")
    public String editarReview(Review review){
        reviewService.actualizarReview(review.getId(), review);
        return "redirect:/review/detalles/"+review.getId();
    }

    @GetMapping("/review/eliminar/{id}")
    public String eliminarReview(@PathVariable Long id){
        reviewService.eliminarReview(id);
        return "redirect:/reviews";
    }

    @GetMapping("/review/detalles/{id}")
    public String mostrarDetallesReview(@PathVariable Long id, Model model){
        Review review = reviewService.getReview(id);
        if (review == null) {
            return "redirect:/";
        }
        model.addAttribute("review", review);
        return "detalles-review";
    }
}
