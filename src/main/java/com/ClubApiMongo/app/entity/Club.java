package com.ClubApiMongo.app.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import jakarta.validation.constraints.NotEmpty;

@Document(collection = "club")
public class Club {

		//Relacion Uno a Muchos
		@DocumentReference
		private Entrenador entrenador;

		//Relacion Uno a Muchos
		@DocumentReference
		//Unir columna mediante el id
		private List<Jugador> jugador;

		//Relacion Muchos a Uno
		@DocumentReference
		private Asociacion asociacion;

		//Relacion Muchos a Muchos
		@DocumentReference
		private List<Competicion> competicion;

		@Id
		private String id;

		@NotEmpty
		private String Nombre;

		public Club() {
			
		}

		public Entrenador getEntrenador() {
			return entrenador;
		}

		public void setEntrenador(Entrenador entrenador) {
			this.entrenador = entrenador;
		}

		public List<Jugador> getJugador() {
			return jugador;
		}

		public void setJugador(List<Jugador> jugador) {
			this.jugador = jugador;
		}

		public Asociacion getAsociacion() {
			return asociacion;
		}

		public void setAsociacion(Asociacion asociacion) {
			this.asociacion = asociacion;
		}

		public List<Competicion> getCompeticion() {
			return competicion;
		}

		public void setCompeticion(List<Competicion> competicion) {
			this.competicion = competicion;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getNombre() {
			return Nombre;
		}

		public void setNombre(String nombre) {
			Nombre = nombre;
		}

		
		
}
