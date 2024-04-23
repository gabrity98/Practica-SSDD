package es.ssdd.PracticaSSDD.controllers;

import es.ssdd.PracticaSSDD.entities.Pelicula;
import es.ssdd.PracticaSSDD.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaRESTController {

    @Autowired
    private PeliculaService peliculaService;

    @PostMapping("/{idUsuario}")
    public ResponseEntity<Pelicula> crearPelicula(@PathVariable Long idUsuario, @RequestBody Pelicula pelicula){
        if (pelicula == null) {
            return ResponseEntity.badRequest().build();
        }
        Pelicula nuevaPelicula = peliculaService.crearPelicula(pelicula, idUsuario);
        if (nuevaPelicula == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(nuevaPelicula);
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
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(peliculaActualizada);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Pelicula> actualizarParcialmentePelicula(@PathVariable Long id, @RequestBody Pelicula parcialPelicula) {
        Pelicula existente = peliculaService.getPelicula(id);
        if (parcialPelicula.getNombre() != null) {
            existente.setNombre(parcialPelicula.getNombre());
        }
        if (parcialPelicula.getGenero() != null) {
            existente.setGenero(parcialPelicula.getGenero());
        }
        if (parcialPelicula.getDirector() != null) {
            existente.setDirector(parcialPelicula.getDirector());
        }
         if (parcialPelicula.getPuntuacion() != null) {
            existente.setPuntuacion(parcialPelicula.getPuntuacion());
        }
        peliculaService.actualizarPelicula(id, existente);
        return ResponseEntity.ok(existente);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Long id){
        peliculaService.eliminarPelicula(id);
        return ResponseEntity.ok().build();
    }
}
