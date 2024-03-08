package es.ssdd.PracticaSSDD.controllers;

import es.ssdd.PracticaSSDD.entities.Pelicula;
import es.ssdd.PracticaSSDD.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaRESTController {

    @Autowired
    private PeliculaService peliculaService;

    @PostMapping
    public ResponseEntity<Pelicula> crearPelicula(@RequestBody Pelicula pelicula){
        return ResponseEntity.status(201).body(peliculaService.crearPelicula(pelicula));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> getPelicula(@PathVariable Long id){
        Pelicula pelicula = peliculaService.getPelicula(id);
        if (pelicula == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pelicula);
    }

    @GetMapping
    public ResponseEntity<Collection<Pelicula>> getAllPeliculas(){
        return ResponseEntity.ok(peliculaService.getAllPeliculas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable Long id, @RequestBody Pelicula pelicula){
        Pelicula peliculaActualizada = peliculaService.actualizarPelicula(id, pelicula);
        if (peliculaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(peliculaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCerveza(@PathVariable Long id){
        peliculaService.eliminarPelicula(id);
        return ResponseEntity.ok().build();
    }
}
