package es.rubengs.clubnautico.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Socio {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	int id;
	
	@Basic
	String nombre;
	String email;
	
	public Socio(int id, String nombre, String email) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
	}
	
	public Socio() {
	}
	
	
	
}
