package es.rubengs.clubnautico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@OneToMany
    @JoinColumn(name = "salida_id", nullable = false)
    private Salida salida;
	public Patron(int id, String nombre, String email,Salida salida) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.salida = salida;
	}
	
	public Patron() {
	}
	
	
	
}
