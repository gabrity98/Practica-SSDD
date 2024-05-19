package es.ssdd.PracticaSSDD;

import es.ssdd.PracticaSSDD.entities.Pelicula;
import es.ssdd.PracticaSSDD.entities.Usuario;
import es.ssdd.PracticaSSDD.repositories.PeliculaRepository;
import es.ssdd.PracticaSSDD.repositories.ReviewRepository;
import es.ssdd.PracticaSSDD.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
    }
}
