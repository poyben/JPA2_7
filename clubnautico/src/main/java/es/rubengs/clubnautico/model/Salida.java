package es.rubengs.clubnautico.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "salidas")
public class Salida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name = "fecha_salida")
	Date fechaSalida;
	String destino;
	float cuota;
	@ManyToOne
	@JoinColumn(name = "patron_id", nullable = false)
	private Patron patron;
	@ManyToOne
	@JoinColumn(name = "barco_id", nullable = false)
	private Barco barco;

	public Salida(int id, Date fechaSalida, String destino, float cuota, Patron patron) {
		this.id = id;
		this.fechaSalida = fechaSalida;
		this.destino = destino;
		this.cuota = cuota;
		this.patron = patron;
	}

	public Salida() {
	}

}
