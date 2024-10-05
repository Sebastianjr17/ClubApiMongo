package com.ClubApiMongo.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;

@Document(collection = "jugador")
public class Jugador {

		@Id
		private String id;

		@NotEmpty
		private String name;

		@NotEmpty
		private String lastName;

		@NotEmpty
		private String numero;

		@NotEmpty
		private String posicion;

		//CONSTRUCTOR
		public Jugador() {
		}

		//METODOS GET y SET
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getNumero() {
			return numero;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}

		public String getPosicion() {
			return posicion;
		}

		public void setPosicion(String posicion) {
			this.posicion = posicion;
		}	
		
}
