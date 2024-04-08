package es.rubengs.clubnautico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "barcos")
public class Barco {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	int id;
	@Column(name="numero_matricula")
	String numeroMatricula;
	String nombre;
	@Column(name="numero_amarre")
	String numeroAmarre;
	float cuota;
	
	
}
