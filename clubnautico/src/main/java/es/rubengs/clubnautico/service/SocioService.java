package es.rubengs.clubnautico.service;

import java.util.ArrayList;
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

		if (socioDto.getBarcos() != null) {
	        List<Barco> barcos = socioDto.getBarcos().stream().map(this::convertDtoToBarco).toList();
	        socio.setBarcos(barcos);
	    } else {
	        socio.setBarcos(new ArrayList<>()); 
	    }

		Socio savedSocio = socioRepo.save(socio);

		List<BarcoDto> barcosDto = savedSocio.getBarcos().stream().map(this::convertBarcoToDto)
				.toList();

		return new SocioDto(savedSocio.getId(), savedSocio.getNombre(), savedSocio.getEmail(), barcosDto);
	}

	public void deleteSocio(int id) {
		socioRepo.deleteById(id);
	}

	public List<SocioDto> findAll() {
		return socioRepo.findAll().stream().map(this::convertToDto).toList();
	}

	public SocioDto findById(int id) {
		return socioRepo.findById(id).map(this::convertToDto).orElse(null);
	}

	public Socio updateSocio(int id, Socio socioDetails) {
		Optional<Socio> socioOptional = socioRepo.findById(id);
		if (socioOptional.isPresent()) {
			Socio existingSocio = socioOptional.get();
			existingSocio.setNombre(socioDetails.getNombre());
			existingSocio.setEmail(socioDetails.getEmail());
			return socioRepo.save(existingSocio);
		} else {
			return null;
		}
	}

	public SocioDto convertToDto(Socio socio) {
		SocioDto socioDto = new SocioDto();
		socioDto.setId(socio.getId());
		socioDto.setNombre(socio.getNombre());
		socioDto.setEmail(socio.getEmail());


		List<BarcoDto> barcosDto = socio.getBarcos().stream().map(this::convertBarcoToDto).toList();

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

		return barcoDto;
	}

	private Barco convertDtoToBarco(BarcoDto barcoDto) {
		Barco barco = new Barco();
		barco.setId(barcoDto.getId());
		barco.setNumeroMatricula(barcoDto.getNumeroMatricula());
		barco.setNombre(barcoDto.getNombre());
		barco.setNumeroAmarre(barcoDto.getNumeroAmarre());
		barco.setCuota(barcoDto.getCuota());

		return barco;
	}

}
