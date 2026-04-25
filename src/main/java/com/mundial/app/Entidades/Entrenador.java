package com.mundial.app.Entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "entrenador")
public class Entrenador {

    @Id
    private String id;

    private String nombre;
    private String apellido;
    private int edad;
    private String nacionalidad;

    // 🔥 CONSTRUCTOR VACÍO (IMPORTANTE)
    public Entrenador() {}

    // 🔥 GETTER Y SETTER DE ID (OBLIGATORIO)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // demás getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
}
