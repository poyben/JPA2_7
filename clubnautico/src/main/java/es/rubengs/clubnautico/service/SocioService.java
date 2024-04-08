package es.rubengs.clubnautico.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rubengs.clubnautico.dto.BarcoDto;
import es.rubengs.clubnautico.dto.PatronDto;
import es.rubengs.clubnautico.dto.SalidaDto;
import es.rubengs.clubnautico.dto.SocioDto;
import es.rubengs.clubnautico.model.Barco;
import es.rubengs.clubnautico.model.Patron;
import es.rubengs.clubnautico.model.Salida;
import es.rubengs.clubnautico.model.Socio;
import es.rubengs.clubnautico.repository.SocioRepository;

@Service
public class SocioService {

	@Autowired
	SocioRepository socioRepo;
	
	public SocioDto createSocio(SocioDto socioDto) {
	    Socio socio = new Socio();
	    socio.setNombre(socioDto.getNombre());
	    socio.setEmail(socioDto.getEmail());
	    
	    // Convertir los BarcoDto a Barco antes de asignarlos al socio
	    List<Barco> barcos = socioDto.getBarcos().stream()
	                                .map(this::convertDtoToBarco)
	                                .collect(Collectors.toList());
	    socio.setBarcos(barcos);
	    
	    Socio savedSocio = socioRepo.save(socio);
	    
	    // Convertir los Barco a BarcoDto antes de retornar el SocioDto
	    List<BarcoDto> barcosDto = savedSocio.getBarcos().stream()
	                                    .map(this::convertBarcoToDto)
	                                    .collect(Collectors.toList());
	    
	    return new SocioDto(savedSocio.getId(), savedSocio.getNombre(), savedSocio.getEmail(), barcosDto);
	}
	
	public void deleteSocio(int id) {
		socioRepo.deleteById(id);
	}
	
	public List<SocioDto> findAll(){
		return socioRepo.findAll().stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
	
	
	public SocioDto findById(int id) {
		return socioRepo.findById(id)
				.map(this::convertToDto)
				.orElse(null);
	}
	
	
	
	public Socio updateSocio(int id, Socio socioDetails) {
        Optional<Socio> socioOptional = socioRepo.findById(id);
        if (socioOptional.isPresent()) {
            Socio existingSocio = socioOptional.get();
            existingSocio.setNombre(socioDetails.getNombre());
            existingSocio.setEmail(socioDetails.getEmail());
            Socio updatedSocio = socioRepo.save(existingSocio);
            return updatedSocio;
        } else {
            return null;
        }
    }
	
	private SocioDto convertToDto(Socio socio) {
	    SocioDto socioDto = new SocioDto();
	    socioDto.setId(socio.getId());
	    socioDto.setNombre(socio.getNombre());
	    socioDto.setEmail(socio.getEmail());

	    // Acceder a los barcos para forzar la carga perezosa
	    socio.getBarcos().size();

	    // Convertir los barcos a DTOs
	    List<BarcoDto> barcosDto = socio.getBarcos().stream()
	                                    .map(this::convertBarcoToDto)
	                                    .collect(Collectors.toList());
	    
	    socioDto.setBarcos(barcosDto);

	    return socioDto;
	}
	
	private BarcoDto convertBarcoToDto(Barco barco) {
	    BarcoDto barcoDto = new BarcoDto();
	    barcoDto.setId(barco.getId());
	    barcoDto.setNumeroMatricula(barco.getNumeroMatricula());
	    barcoDto.setNombre(barco.getNombre());
	    barcoDto.setNumeroAmarre(barco.getNumeroAmarre());
	    barcoDto.setCuota(barco.getCuota());
	    // No convertir socio aquí, ya que puede causar recursión infinita
	    return barcoDto;
	}

	 private SalidaDto convertSalidaToDto(Salida salida) {
	        PatronDto patronDto = null;
	        if (salida.getPatron() != null) {
	            patronDto = new PatronDto(
	                    salida.getPatron().getId(),
	                    salida.getPatron().getNombre(),
	                    salida.getPatron().getEmail()
	            );
	        }

	        int barcoId = salida.getBarco() != null ? salida.getBarco().getId() : 0;

	        return new SalidaDto(
	                salida.getId(),
	                salida.getFechaSalida(),
	                salida.getDestino(),
	                salida.getCuota(),
	                patronDto,
	                barcoId
	        );
	    }
	
	 private Barco convertDtoToBarco(BarcoDto barcoDto) {
		    Barco barco = new Barco();
		    barco.setId(barcoDto.getId());
		    barco.setNumeroMatricula(barcoDto.getNumeroMatricula());
		    barco.setNombre(barcoDto.getNombre());
		    barco.setNumeroAmarre(barcoDto.getNumeroAmarre());
		    barco.setCuota(barcoDto.getCuota());
		    // No es necesario establecer el socio aquí, ya que se establece cuando se asigna al socio.
		    return barco;
		}
}
