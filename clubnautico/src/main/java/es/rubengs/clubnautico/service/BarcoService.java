package es.rubengs.clubnautico.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
import es.rubengs.clubnautico.repository.BarcoRepository;
import es.rubengs.clubnautico.repository.SocioRepository;

@Service
public class BarcoService {

	@Autowired
	BarcoRepository barcoRepo;
	@Autowired
	SocioRepository socioRepo;

	public BarcoDto createBarco(BarcoDto barcoDto) {
		Barco barco = new Barco();
		barco.setNombre(barcoDto.getNombre());
		barco.setNumeroAmarre(barcoDto.getNumeroAmarre());
		barco.setNumeroMatricula(barcoDto.getNumeroMatricula());
		barco.setCuota(barcoDto.getCuota());
		List<Salida> salidas = barcoDto.getSalidas().stream().map(this::convertSalidaDtoToEntity).toList();
		barco.setSalidas(salidas);
		Barco saved = barcoRepo.save(barco);
		List<SalidaDto> salidasDto = saved.getSalidas().stream().map(this::convertSalidaToDto).toList();
		return new BarcoDto(saved.getId(), saved.getNumeroMatricula(), saved.getNombre(),
				saved.getCuota(), salidasDto, saved.getNumeroAmarre());
	}

	public void deleteBarco(int id) {
		barcoRepo.deleteById(id);
	}

	public List<BarcoDto> findAll() {
		return barcoRepo.findAll().stream().map(this::convertToDto).toList();
	}

	public BarcoDto findById(int id) {
		return barcoRepo.findById(id).map(this::convertToDto).orElse(null);
	}

	public BarcoDto updateBarco(int id, BarcoDto barcoDetails) {
		Optional<Barco> barcoOptional = barcoRepo.findById(id);
		if (barcoOptional.isPresent()) {
			Barco existingBarco = barcoOptional.get();
			existingBarco.setNombre(barcoDetails.getNombre());
			existingBarco.setCuota(barcoDetails.getCuota());
			List<Salida> salidas = barcoDetails.getSalidas().stream()
					.map(this::convertSalidaDtoToEntity).toList();

			existingBarco.setSalidas(salidas);

			existingBarco.setNumeroAmarre(barcoDetails.getNumeroAmarre());
			existingBarco.setNumeroMatricula(barcoDetails.getNumeroMatricula());
			Barco updatedBarco = barcoRepo.save(existingBarco);

			List<SalidaDto> salidasDto = salidas.stream().map(this::convertSalidaToDto)
					.toList();

			return new BarcoDto(updatedBarco.getId(), updatedBarco.getNumeroMatricula(), updatedBarco.getNombre(),
					barcoDetails.getCuota(), salidasDto, updatedBarco.getNumeroAmarre(), barcoDetails.getSocioDto());
		} else {
			return null;
		}
	}

	private Salida convertSalidaDtoToEntity(SalidaDto salidaDto) {
		Salida salida = new Salida();
		salida.setCuota(salidaDto.getCuota());
		salida.setDestino(salidaDto.getDestino());
		return salida;
	}

	private SalidaDto convertSalidaToDto(Salida salida) {
		PatronDto patronDto = null;
		if (salida.getPatron() != null) {
			patronDto = new PatronDto(salida.getPatron().getId(), salida.getPatron().getNombre(),
					salida.getPatron().getEmail());
		}

		return new SalidaDto(salida.getId(), salida.getFechaSalida(), salida.getDestino(), salida.getCuota(), patronDto,
				salida.getBarco() != null ? salida.getBarco().getId() : null);
	}

	private BarcoDto convertToDto(Barco barco) {
		BarcoDto barcoDto = new BarcoDto();
		barcoDto.setId(barco.getId());
		barcoDto.setNumeroMatricula(barco.getNumeroMatricula());
		barcoDto.setNombre(barco.getNombre());
		barcoDto.setNumeroAmarre(barco.getNumeroAmarre());
		barcoDto.setCuota(barco.getCuota());

		if (barco.getSocio() != null) {
			SocioDto socioDto = new SocioDto();
			socioDto.setId(barco.getSocio().getId());
			socioDto.setNombre(barco.getSocio().getNombre());
			socioDto.setEmail(barco.getSocio().getEmail());
			barcoDto.setSocioDto(socioDto);
		} else {
			SocioDto socioDto = new SocioDto();
			barcoDto.setSocioDto(socioDto);
		}

		List<SalidaDto> salidasDto = null;
		if (barco.getSalidas() != null && !barco.getSalidas().isEmpty()) {
			salidasDto = barco.getSalidas().stream().map(this::convertSalidaToDto).toList();
		}
		barcoDto.setSalidas(salidasDto);

		return barcoDto;
	}

	public BarcoDto createBarcoWithSocio(Integer socioId, BarcoDto barcoDto) {
		Socio socio = socioRepo.findById(socioId)
				.orElseThrow(() -> new NoSuchElementException("Socio no encontrado con ID: " + socioId));

		Barco barco = convertToEntity(barcoDto);
		barco.setSocio(socio);

		socio.getBarcos().add(barco);

		Barco savedBarco = barcoRepo.save(barco);
		return convertToDto(savedBarco);
	}

	private Barco convertToEntity(BarcoDto barcoDto) {
		Barco barco = new Barco();
		barco.setNumeroMatricula(barcoDto.getNumeroMatricula());
		barco.setNombre(barcoDto.getNombre());
		barco.setNumeroAmarre(barcoDto.getNumeroAmarre());
		barco.setCuota(barcoDto.getCuota());
		return barco;
	}
	
	
	public BarcoDto partialUpdateBarco(int id, Map<String, Object> updates) {
        Optional<Barco> barcoOptional = barcoRepo.findById(id);
        if (barcoOptional.isPresent()) {
            Barco existingBarco = barcoOptional.get();

            updates.forEach((key, value) -> {
                switch (key) {
                    case "nombre":
                        existingBarco.setNombre((String) value);
                        break;
                    case "cuota":
                        existingBarco.setCuota((double) value);
                        break;
                    case "numeroAmarre":
                        existingBarco.setNumeroAmarre((int) value);
                        break;
                    case "numeroMatricula":
                        existingBarco.setNumeroMatricula((String) value);
                        break;
                    default:
                        break;
                }
            });

            Barco updatedBarco = barcoRepo.save(existingBarco);
            return convertToDto(updatedBarco);
        } else {
            return null;
        }
    }
	
}