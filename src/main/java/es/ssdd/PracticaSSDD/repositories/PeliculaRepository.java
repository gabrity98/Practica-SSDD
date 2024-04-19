package es.ssdd.PracticaSSDD.repositories;

import es.ssdd.PracticaSSDD.entities.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
}
