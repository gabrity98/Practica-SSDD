package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Pelicula;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class PeliculaService {
    private final Map<Long, Pelicula> peliculas = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public Pelicula crearPelicula(Pelicula pelicula){
        if (pelicula.getNombre() == null || pelicula.getDirector() == null || pelicula.getGenero() == null || pelicula.getPuntuacion() == null)
            return null;
        long id = nextId.incrementAndGet();
        pelicula.setId(id);
        peliculas.put(id,pelicula);
        return pelicula;
    }

    public Pelicula getPelicula(Long id){
        return peliculas.get(id);
    }

    public Collection<Pelicula> getAllPeliculas(){
        return peliculas.values();
    }

    public Pelicula actualizarPelicula(Long id, Pelicula pelicula){
        if (!peliculas.containsKey(id))
            return null;
        if (pelicula.getNombre() == null || pelicula.getDirector() == null || pelicula.getGenero() == null || pelicula.getPuntuacion() == null)
            return null;
        pelicula.setId(id);
        peliculas.put(id, pelicula);
        return pelicula;
    }

    public void eliminarPelicula(Long id){
        peliculas.remove(id);
    }
}
