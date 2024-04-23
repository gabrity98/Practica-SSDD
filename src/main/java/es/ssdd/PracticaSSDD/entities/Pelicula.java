package es.ssdd.PracticaSSDD.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String genero;
    private String director;
    private Double puntuacion;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(mappedBy = "peliculas")
    @JsonBackReference
    private Set<Usuario> usuarios = new HashSet<>();

    public Pelicula(){}

    public Pelicula(Long id, String nombre, String genero, String director, Double puntuacion){
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.director = director;
        this.puntuacion = puntuacion;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getDirector() {
        return director;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
