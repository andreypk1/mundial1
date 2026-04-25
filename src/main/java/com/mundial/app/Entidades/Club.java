package com.mundial.app.Entidades;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "club")
public class Club {

   @Id
   private String id;

   private String nombre;
   private String ubicacion;
   private String estadio;
   private Integer anioFundacion;

   @DocumentReference
   private Asociacion asociacion;

   @DocumentReference
   private Entrenador entrenador;

   @DocumentReference
   private List<Jugador> jugadores;

   // 🔥 CONSTRUCTOR VACÍO
   public Club() {}

   // 🔥 ID (OBLIGATORIO)
   public String getId() {
       return id;
   }

   public void setId(String id) {
       this.id = id;
   }

   // campos normales
   public String getNombre() {
       return nombre;
   }

   public void setNombre(String nombre) {
       this.nombre = nombre;
   }

   public String getUbicacion() {
       return ubicacion;
   }

   public void setUbicacion(String ubicacion) {
       this.ubicacion = ubicacion;
   }

   public String getEstadio() {
       return estadio;
   }

   public void setEstadio(String estadio) {
       this.estadio = estadio;
   }

   public Integer getAnioFundacion() {
       return anioFundacion;
   }

   public void setAnioFundacion(Integer anioFundacion) {
       this.anioFundacion = anioFundacion;
   }

   // 🔥 RELACIONES (MUY IMPORTANTE)

   public Asociacion getAsociacion() {
       return asociacion;
   }

   public void setAsociacion(Asociacion asociacion) {
       this.asociacion = asociacion;
   }

   public Entrenador getEntrenador() {
       return entrenador;
   }

   public void setEntrenador(Entrenador entrenador) {
       this.entrenador = entrenador;
   }

   public List<Jugador> getJugadores() {
       return jugadores;
   }

   public void setJugadores(List<Jugador> jugadores) {
       this.jugadores = jugadores;
   }
}             