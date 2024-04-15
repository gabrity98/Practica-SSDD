package es.ssdd.PracticaSSDD.repositories;

import es.ssdd.PracticaSSDD.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
