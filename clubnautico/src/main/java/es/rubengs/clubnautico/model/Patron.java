package es.rubengs.clubnautico.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "patrones")
public class Patron {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	int id;
	String nombre;
	String email;
	@OneToMany(mappedBy = "patron")
    private List<Salida> salidas;
	public Patron(int id, String nombre, String email,List<Salida> salidas) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.salidas = salidas;
	}
	
	public Patron() {
	}
	
	
	
}
