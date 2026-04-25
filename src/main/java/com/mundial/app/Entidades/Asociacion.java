package com.mundial.app.Entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "asociacion")
public class Asociacion {

    @Id
    private String id;

    private String nombre;
    private String pais;
    private String presidente;

    // 🔥 CONSTRUCTOR VACÍO
    public Asociacion() {}

    // 🔥 ID (OBLIGATORIO)
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }
}
