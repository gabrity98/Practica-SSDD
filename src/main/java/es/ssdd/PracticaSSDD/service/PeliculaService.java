package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Pelicula;
import es.ssdd.PracticaSSDD.repositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class PeliculaService {
    @Autowired
    private PeliculaRepository peliculaRepository;

    public Pelicula crearPelicula(Pelicula pelicula){
        if (pelicula.getNombre() == null || pelicula.getDirector() == null || pelicula.getGenero() == null || pelicula.getPuntuacion() == null)
            return null;
        peliculaRepository.save(pelicula);
        return pelicula;
    }

    public Pelicula getPelicula(Long id){
        return peliculaRepository.getReferenceById(id);
    }

    public Collection<Pelicula> getAllPeliculas(){
        return peliculaRepository.findAll();
    }

    public Pelicula actualizarPelicula(Long id, Pelicula pelicula){
        if (!peliculaRepository.existsById(id))
            return null;
        if (pelicula.getNombre() == null || pelicula.getDirector() == null || pelicula.getGenero() == null || pelicula.getPuntuacion() == null)
            return null;
        Pelicula miPelicula = peliculaRepository.getReferenceById(id);
        miPelicula = pelicula;
        return pelicula;
    }

    public void eliminarPelicula(Long id){
        peliculaRepository.deleteById(id);
    }
}
