package es.ssdd.PracticaSSDD.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "usuario_peliculas",
            joinColumns =@JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id")
    )
    @JsonManagedReference
    private Set<Pelicula> peliculas = new HashSet<>();

    public Usuario(){}
    public Usuario(Long id, String nombre, String email){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
}
