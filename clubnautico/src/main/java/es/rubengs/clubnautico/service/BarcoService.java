package es.rubengs.clubnautico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rubengs.clubnautico.model.Barco;
import es.rubengs.clubnautico.repository.BarcoRepository;

@Service
public class BarcoService {

	@Autowired
	BarcoRepository barcoRepo;
	
	public Barco createPatron(Barco barco) {
		return barcoRepo.save(barco);
	}
	
	public void deleteBarco(int id) {
		barcoRepo.deleteById(id);
	}
	
	public List<Barco> findAll(){
		return barcoRepo.findAll();
	}
	
	public Barco findById(int id) {
		return barcoRepo.findById(id).orElse(null);
	}
	
	public Barco updateBarco(int id, Barco barcoDetails) {
        Optional<Barco> barcoOptional = barcoRepo.findById(id);
        if (barcoOptional.isPresent()) {
        	Barco existingBarco = barcoOptional.get();
            existingBarco.setNombre(barcoDetails.getNombre());
            existingBarco.setCuota(barcoDetails.getCuota());
            existingBarco.setNumeroAmarre(barcoDetails.getNumeroAmarre());
            existingBarco.setNumeroMatricula(barcoDetails.getNumeroMatricula());
            Barco updatedBarco = barcoRepo.save(existingBarco);
            return updatedBarco;
        } else {
            return null;
        }
    }
	
}
