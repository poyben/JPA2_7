package es.rubengs.clubnautico.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rubengs.clubnautico.dto.PatronDto;
import es.rubengs.clubnautico.dto.SalidaDto;
import es.rubengs.clubnautico.model.Patron;
import es.rubengs.clubnautico.model.Salida;
import es.rubengs.clubnautico.repository.BarcoRepository;
import es.rubengs.clubnautico.repository.PatronRepository;
import es.rubengs.clubnautico.repository.SalidaRepository;

@Service
public class SalidaService {

	@Autowired
	SalidaRepository salidaRepo;
	@Autowired
	BarcoRepository barcoRepo;
	@Autowired
	PatronRepository patronRepo;
	
	public SalidaDto createSalida(SalidaDto salidaDto) {
		Salida salida = new Salida();
        barcoRepo.findById(salidaDto.getBarcoId()).ifPresent(salida::setBarco);		
        salida.setCuota(salidaDto.getCuota());
		salida.setDestino(salidaDto.getDestino());
		salida.setFechaSalida(salidaDto.getFechaSalida());
		if (salidaDto.getPatronDto() != null && salidaDto.getPatronDto().getId() > 0) {
	        patronRepo.findById(salidaDto.getPatronDto().getId()).ifPresent(patron -> {
	            salida.setPatron(patron);
	        });
	    } else {
	        System.out.println("No se ha proporcionado un ID de Patron válido. Creando Salida sin asociar un Patron.");
	    }

	    Salida savedSalida = salidaRepo.save(salida);
	    return convertToDto(savedSalida);
	}
	
	
	public void deleteSalida(int id) {
		salidaRepo.deleteById(id);
	}
	
	public List<SalidaDto> findAllDtos() {
	    return salidaRepo.findAll().stream()
	            .map(this::convertToDto)
	            .collect(Collectors.toList());
	}

	
	
	public SalidaDto findByIdDto(int id) {
	    return salidaRepo.findById(id)
	            .map(this::convertToDto)
	            .orElse(null); // Considera retornar una excepción personalizada si es apropiado
	}
	
	
	public SalidaDto updateSalida(int id, SalidaDto salidaDetails) {
        return salidaRepo.findById(id).map(existingSalida -> {
            existingSalida.setCuota(salidaDetails.getCuota());
            existingSalida.setDestino(salidaDetails.getDestino());
            existingSalida.setFechaSalida(salidaDetails.getFechaSalida());
            barcoRepo.findById(salidaDetails.getBarcoId()).ifPresent(existingSalida::setBarco);
            if (salidaDetails.getPatronDto() != null) {
                Patron patron;
                // Si el ID del patron es mayor que 0, intentamos encontrar el Patron existente
                if (salidaDetails.getPatronDto().getId() > 0) {
                    patron = patronRepo.findById(salidaDetails.getPatronDto().getId()).orElse(new Patron());
                } else {
                    // No se proporcionó un ID válido, asumimos que es un nuevo Patron
                    patron = new Patron();
                }
                patron.setNombre(salidaDetails.getPatronDto().getNombre());
                patron.setEmail(salidaDetails.getPatronDto().getEmail());
                // Aquí se pueden establecer más propiedades del Patron desde PatronDto según sea necesario
                // Guardar el Patron (ya sea nuevo o actualizado)
                patron = patronRepo.save(patron);

                // Asociar el Patron a la Salida
                existingSalida.setPatron(patron);
            }
            Salida updatedSalida = salidaRepo.save(existingSalida);
            return convertToDto(updatedSalida);
        }).orElse(null); 
    }
	
	
	private SalidaDto convertToDto(Salida salida) {
	    SalidaDto salidaDTO = new SalidaDto();
	    salidaDTO.setId(salida.getId());
	    salidaDTO.setFechaSalida(salida.getFechaSalida());
	    salidaDTO.setDestino(salida.getDestino());
	    salidaDTO.setCuota(salida.getCuota());
	    if (salida.getBarco() != null) {
	        salidaDTO.setBarcoId(salida.getBarco().getId());
	    }
	    
	    Patron patron = salida.getPatron();
	    if (patron != null) {
	        PatronDto patronDto = convertPatronToDto(patron);
	        salidaDTO.setPatronDto(patronDto); // Asignar objeto PatronDto a SalidaDto
	    }

	    return salidaDTO;
	}

	private PatronDto convertPatronToDto(Patron patron) {
	    return new PatronDto(patron.getId(), patron.getNombre(), patron.getEmail());
	}


	
}

