package es.ssdd.PracticaSSDD.entities;

public class Review {
    private Long id;
    private String autor;
    private String contenido;
    private double puntuacion;

    public Review(){}
    public Review(Long id, String autor, String contenido, double puntuacion){
        this.id = id;
        this.autor = autor;
        this.contenido = contenido;
        this.puntuacion = puntuacion;
    }

    public Long getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String getContenido() {
        return contenido;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }
}
