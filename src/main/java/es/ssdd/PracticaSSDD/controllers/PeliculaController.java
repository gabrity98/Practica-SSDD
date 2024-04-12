package es.ssdd.PracticaSSDD.controllers;

import es.ssdd.PracticaSSDD.entities.Pelicula;
import es.ssdd.PracticaSSDD.service.PeliculaService;
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

    @GetMapping("/pelicula/agregar")
    public String mostrarFormularioAgregar(Model model){
        model.addAttribute("pelicula", new Pelicula());
        return "agregar-pelicula";
    }

    @PostMapping("/pelicula/agregar")
    public String agregarPelicula(Pelicula pelicula){
        peliculaService.crearPelicula(pelicula);
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
    public String mostrarDetallesPelicula(@PathVariable Long id, Model model){
        Pelicula pelicula = peliculaService.getPelicula(id);
        if (pelicula == null) {
            return "redirect:/peliculas";
        }
        model.addAttribute("pelicula", pelicula);
        return "detalles-pelicula";
    }
}
