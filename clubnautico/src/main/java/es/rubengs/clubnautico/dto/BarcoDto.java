package es.rubengs.clubnautico.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BarcoDto {
	private int id;
	private String numeroMatricula;
	private String nombre;
	private SocioDto socioDto;
	private List<SalidaDto> salidas;
	private int numeroAmarre;
	private double cuota;

	

	public BarcoDto(int id, String numeroMatricula, String nombre, double cuota, List<SalidaDto> salidas,
			int numeroAmarre, SocioDto socioDto) {
		this.id = id;
		this.numeroMatricula = numeroMatricula;
		this.nombre = nombre;
		this.socioDto = socioDto;
		this.salidas = salidas;
		this.numeroAmarre = numeroAmarre;
		this.cuota = cuota;
	}

	public BarcoDto() {
		this.salidas = new ArrayList<>();
	}

	public BarcoDto(int id, String numeroMatricula, String nombre, List<SalidaDto> salidas, int numeroAmarre,
			double cuota) {
		this.id = id;
		this.numeroMatricula = numeroMatricula;
		this.nombre = nombre;
		this.salidas = salidas;
		this.numeroAmarre = numeroAmarre;
		this.cuota = cuota;
	}

	public BarcoDto(int id, String numeroMatricula, String nombre, double cuota, List<SalidaDto> salidas,
			int numeroAmarre) {
		this.id = id;
		this.numeroMatricula = numeroMatricula;
		this.nombre = nombre;
		this.salidas = salidas;
		this.numeroAmarre = numeroAmarre;
		this.cuota = cuota;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroMatricula() {
		return numeroMatricula;
	}

	public void setNumeroMatricula(String numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public SocioDto getSocioDto() {
		return socioDto;
	}

	public void setSocioDto(SocioDto socioDto) {
		this.socioDto = socioDto;
	}

	public List<SalidaDto> getSalidas() {
		return salidas;
	}

	public void setSalidas(List<SalidaDto> salidas) {
		this.salidas = salidas;
	}

	public int getNumeroAmarre() {
		return numeroAmarre;
	}

	public void setNumeroAmarre(int numeroAmarre) {
		this.numeroAmarre = numeroAmarre;
	}

	public double getCuota() {
		return cuota;
	}

	public void setCuota(double cuota) {
		this.cuota = cuota;
	}

	

}
