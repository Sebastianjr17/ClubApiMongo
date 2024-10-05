package com.ClubApiMongo.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotEmpty;

@Document(collection = "asociacion")
public class Asociacion {

	@Id
	private String id;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String pais;

	@NotEmpty
	private String presidente;

	// CONSTRUCTOR
	public Asociacion() {
	}

	// METODOS GET y SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
