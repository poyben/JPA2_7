package es.rubengs.clubnautico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "socios")
public class Socio {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	int id;
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
