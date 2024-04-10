package es.rubengs.clubnautico.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rubengs.clubnautico.dto.PatronDto;
import es.rubengs.clubnautico.model.Patron;
import es.rubengs.clubnautico.repository.PatronRepository;

@Service
public class PatronService {

	@Autowired
	PatronRepository patronRepo;

	public PatronDto createPatron(PatronDto patronDTO) {
		Patron patron = new Patron();
		patron.setNombre(patronDTO.getNombre());
		patron.setEmail(patronDTO.getEmail());
		Patron savedPatron = patronRepo.save(patron);
		return new PatronDto(savedPatron.getId(), savedPatron.getNombre(), savedPatron.getEmail());
	}

	public void deletePatron(int id) {
		patronRepo.deleteById(id);
	}

	public List<PatronDto> findAllDTOs() {
		List<Patron> patrons = patronRepo.findAll();
		return patrons.stream().map(patron -> new PatronDto(patron.getId(), patron.getNombre(), patron.getEmail()))
				.collect(Collectors.toList());
	}

	public PatronDto findByIdDTO(int id) {
		Optional<Patron> patronOptional = patronRepo.findById(id);
		if (patronOptional.isPresent()) {
			Patron patron = patronOptional.get();
			return new PatronDto(patron.getId(), patron.getNombre(), patron.getEmail());
		} else {
			return null;
		}
	}

	public PatronDto updatePatron(int id, PatronDto patronDetails) {
		Optional<Patron> patronOptional = patronRepo.findById(id);
		if (patronOptional.isPresent()) {
			Patron existingPatron = patronOptional.get();
			existingPatron.setNombre(patronDetails.getNombre());
			existingPatron.setEmail(patronDetails.getEmail());
			Patron updatedPatron = patronRepo.save(existingPatron);
			return new PatronDto(updatedPatron.getId(), updatedPatron.getNombre(), updatedPatron.getEmail());
		} else {
			return null;
		}
	}

}
