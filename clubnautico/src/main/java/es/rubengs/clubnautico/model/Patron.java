package es.rubengs.clubnautico.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "patrones")
public class Patron {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String nombre;
	String email;
	@OneToMany(mappedBy = "patron", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Salida> salidas;


	public Patron() {
		//Default constructor
	}

}
