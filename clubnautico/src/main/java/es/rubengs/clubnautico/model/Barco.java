package es.rubengs.clubnautico.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
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
@Table(name = "barcos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Barco {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	int id;
	@Column(name="numero_matricula")
	String numeroMatricula;
	String nombre;
	@Column(name="numero_amarre")
	int numeroAmarre;
	float cuota;
	@ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
	@JsonBackReference
    private Socio socio;
	@OneToMany(mappedBy= "barco")
    private List<Salida> salidas;
	
	
	public Barco(int id, String numeroMatricula, String nombre, int numeroAmarre, float cuota, Socio socio
			/*,List<Salida> salidas*/) {
		this.id = id;
		this.numeroMatricula = numeroMatricula;
		this.nombre = nombre;
		this.numeroAmarre = numeroAmarre;
		this.cuota = cuota;
		this.socio = socio;
		//this.salidas = salidas;
	}
	public Barco() {
	}
	
	
	
}
