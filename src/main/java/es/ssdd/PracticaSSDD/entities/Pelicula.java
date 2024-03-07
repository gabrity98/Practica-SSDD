package es.ssdd.PracticaSSDD.entities;

public class Pelicula {
    private Long id;
    private String nombre;
    private String genero;
    private String director;
    private Double puntuacion;

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
}
