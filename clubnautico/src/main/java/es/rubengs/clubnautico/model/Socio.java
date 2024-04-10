package es.rubengs.clubnautico.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "socios")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Socio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String nombre;
	String email;
	@OneToMany(mappedBy = "socio", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Barco> barcos;

	public Socio(int id, String nombre, String email, List<Barco> barcos) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.barcos = barcos;
	}

	public Socio() {
	}

}
