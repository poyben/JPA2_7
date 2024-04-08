package es.rubengs.clubnautico.dto;

import java.util.List;

public class SocioDto {

	  private int id;
	    private String nombre;
	    private String email;
	    private List<BarcoDto> barcos;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public List<BarcoDto> getBarcos() {
			return barcos;
		}
		public void setBarcos(List<BarcoDto> barcos) {
			this.barcos = barcos;
		}
		public SocioDto(int id, String nombre, String email, List<BarcoDto> barcos) {
			this.id = id;
			this.nombre = nombre;
			this.email = email;
			this.barcos = barcos;
		}
		public SocioDto() {
		}
	    
	    
	
}
