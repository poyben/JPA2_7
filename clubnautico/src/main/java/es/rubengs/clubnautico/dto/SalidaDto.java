package es.rubengs.clubnautico.dto;

import java.util.Date;

import es.rubengs.clubnautico.model.Barco;
import es.rubengs.clubnautico.model.Patron;

public class SalidaDto {

	private int id;
    private Date fechaSalida;
    private String destino;
    private float cuota;
    private PatronDto patronDto;
    private int barcoId; 

    public SalidaDto(int id, Date fechaSalida, String destino, float cuota, PatronDto patronDto, int barcoId) {
        this.id = id;
        this.fechaSalida = fechaSalida;
        this.destino = destino;
        this.cuota = cuota;
        this.patronDto = patronDto;
        this.barcoId = barcoId;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public float getCuota() {
		return cuota;
	}

	public void setCuota(float cuota) {
		this.cuota = cuota;
	}

	public PatronDto getPatronDto() {
		return patronDto;
	}

	public void setPatronDto(PatronDto patronDto) {
		this.patronDto = patronDto;
	}

	public int getBarcoId() {
		return barcoId;
	}

	public void setBarcoId(int barcoId) {
		this.barcoId = barcoId;
	}

	public SalidaDto() {
	}
    
    
	
}
