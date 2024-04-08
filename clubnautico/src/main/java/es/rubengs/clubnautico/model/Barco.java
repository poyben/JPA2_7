package es.rubengs.clubnautico.model;

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
	@ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    private Socio socio;
	@OneToMany
    @JoinColumn(name = "salida_id", nullable = false)
    private Salida salida;
	
	
	public Barco(int id, String numeroMatricula, String nombre, String numeroAmarre, float cuota, Socio socio,
			Salida salida) {
		this.id = id;
		this.numeroMatricula = numeroMatricula;
		this.nombre = nombre;
		this.numeroAmarre = numeroAmarre;
		this.cuota = cuota;
		this.socio = socio;
		this.salida = salida;
	}
	public Barco() {
	}
	
	
	
}
