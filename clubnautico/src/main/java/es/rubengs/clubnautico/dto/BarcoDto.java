package es.rubengs.clubnautico.dto;

import java.util.List;

import es.rubengs.clubnautico.model.Salida;
import es.rubengs.clubnautico.model.Socio;

public class BarcoDto {
	    private int id;
	    private String numeroMatricula;
	    private String nombre;
	    private SocioDto socioDto;
	    private List<SalidaDto> salidas;
	    private int numeroAmarre;
	    private float cuota;
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
	
		public BarcoDto(int id, String numeroMatricula, String nombre,  float cuota,List<SalidaDto> salidas, int numeroAmarre,SocioDto socioDto ) {
		    this.id = id;
		    this.numeroMatricula = numeroMatricula;
		    this.nombre = nombre;
		    this.socioDto = socioDto;
		    this.salidas = salidas;
		    this.numeroAmarre = numeroAmarre;
		    this.cuota = cuota;
		}


		public BarcoDto() {
		}
	
		
		public BarcoDto(int id, String numeroMatricula, String nombre, List<SalidaDto> salidas, int numeroAmarre,
				float cuota) {
			this.id = id;
			this.numeroMatricula = numeroMatricula;
			this.nombre = nombre;
			this.salidas = salidas;
			this.numeroAmarre = numeroAmarre;
			this.cuota = cuota;
		}
		
		
		public SocioDto getSocio() {
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
		public float getCuota() {
			return cuota;
		}
		public void setCuota(float cuota) {
			this.cuota = cuota;
		}
		public SocioDto getSocioDto() {
			return socioDto;
		}
	   
	    
	    
	    
	}

