package es.ssdd.PracticaSSDD.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String genero;
    private String director;
    private Double puntuacion;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(mappedBy = "peliculas")
    private Set<Usuario> usuarios = new HashSet<>();

    public Pelicula(Long id, String nombre, String genero, String director, Double puntuacion){
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.director = director;
        this.puntuacion = puntuacion;
    }
}
