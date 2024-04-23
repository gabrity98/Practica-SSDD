package es.ssdd.PracticaSSDD.controllers;

import es.ssdd.PracticaSSDD.entities.Pelicula;
import es.ssdd.PracticaSSDD.service.PeliculaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/peliculas")
    public String listarPeliculas(Model model){
        model.addAttribute("peliculas", peliculaService.getAllPeliculas());
        return "peliculas";
    }

    @GetMapping("/misPeliculas")
    public String listarMisPeliculas(Model model, HttpSession session){
        Long userID = (Long) session.getAttribute("userID");
        model.addAttribute("peliculas", peliculaService.getAllUserPeliculas(userID));
        return "mis-peliculas";
    }

    @PostMapping("/misPeliculas/agregar/{id}")
    public String favoritePelicula(@PathVariable Long id, HttpSession session){
        Long userID = (Long) session.getAttribute("userID");
        peliculaService.favoritePelicula(id, userID);
        return "redirect:/misPeliculas";
    }

    @PostMapping("/misPeliculas/eliminar/{id}")
    public String eliminarFavoritos(@PathVariable Long id, HttpSession session){
        Long userID = (Long) session.getAttribute("userID");
        peliculaService.eliminarFavoritos(id, userID);
        return "redirect:/misPeliculas";
    }

    @GetMapping("/pelicula/agregar")
    public String mostrarFormularioAgregar(Model model){
        model.addAttribute("pelicula", new Pelicula());
        return "agregar-pelicula";
    }

    @PostMapping("/pelicula/agregar")
    public String agregarPelicula(Pelicula pelicula, HttpSession session){
        Long userID = (Long) session.getAttribute("userID");
        peliculaService.crearPelicula(pelicula, userID);
        return "redirect:/peliculas";
    }

    @GetMapping("/pelicula/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model){
        Pelicula pelicula = peliculaService.getPelicula(id);
        if (pelicula == null)
            return "redirect:/peliculas";
        model.addAttribute("pelicula",pelicula);
        return "editar-pelicula";
    }

    @PostMapping("/pelicula/editar")
    public String editarPelicula(Pelicula pelicula){
        peliculaService.actualizarPelicula(pelicula.getId(), pelicula);
        return "redirect:/peliculas";
    }

    @GetMapping("/pelicula/eliminar/{id}")
    public String eliminarPelicula(@PathVariable Long id){
        peliculaService.eliminarPelicula(id);
        return "redirect:/peliculas";
    }

    @GetMapping("/pelicula/detalles/{id}")
    public String mostrarDetallesPelicula(@PathVariable Long id, Model model, HttpSession session){
        Pelicula pelicula = peliculaService.getPelicula(id);
        if (pelicula == null) {
            return "redirect:/peliculas";
        }
        session.setAttribute("peliculaID", id);
        model.addAttribute("pelicula", pelicula);
        return "detalles-pelicula";
    }
}
