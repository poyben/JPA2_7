package es.rubengs.clubnautico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rubengs.clubnautico.model.Patron;
import es.rubengs.clubnautico.repository.PatronRepository;

@Service
public class PatronService {
	
	@Autowired
	PatronRepository patronRepo;
	
	public Patron createPatron(Patron patron) {
		return patronRepo.save(patron);
	}
	
	public void deletePatron(int id) {
		patronRepo.deleteById(id);
	}
	
	public List<Patron> findAll(){
		return patronRepo.findAll();
	}
	
	public Patron findById(int id) {
		return patronRepo.findById(id).orElse(null);
	}
	
	public Patron updatePatron(int id, Patron patronDetails) {
        Optional<Patron> patronOptional = patronRepo.findById(id);
        if (patronOptional.isPresent()) {
        	Patron existingPatron = patronOptional.get();
            existingPatron.setNombre(patronDetails.getNombre());
            existingPatron.setEmail(patronDetails.getEmail());
            existingPatron.setSalida(patronDetails.getSalida());
            Patron updatedPatron = patronRepo.save(existingPatron);
            return updatedPatron;
        } else {
            return null;
        }
    }
	
}
