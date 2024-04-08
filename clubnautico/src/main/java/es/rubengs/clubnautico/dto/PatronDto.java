package es.rubengs.clubnautico.dto;

public class PatronDto {

	private int id;
    private String nombre;
    private String email;
    //private int salidaId;
	
    public PatronDto(int id, String nombre, String email/*, int salidaId*/) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        //this.salidaId = salidaId;
    }

	public PatronDto() {
		// TODO Auto-generated constructor stub
	}

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
/*
	public int getSalidaId() {
		return salidaId;
	}

	public void setSalidaId(int salidaId) {
		this.salidaId = salidaId;
	}
    */
    
    
}
