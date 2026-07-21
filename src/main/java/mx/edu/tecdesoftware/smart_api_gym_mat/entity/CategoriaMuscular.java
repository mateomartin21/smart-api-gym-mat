package mx.edu.tecdesoftware.smart_api_gym_mat.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias_musculares")
public class CategoriaMuscular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "categoriaMuscular", cascade = CascadeType.ALL)
    private List<Ejercicio> ejercicios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}