package es.rubengs.clubnautico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rubengs.clubnautico.model.Socio;
import es.rubengs.clubnautico.repository.SocioRepository;

@Service
public class SocioService {

	@Autowired
	SocioRepository socioRepo;
	
	public Socio createSocio(Socio socio) {
		return socioRepo.save(socio);
	}
	
	public void deleteSocio(int id) {
		socioRepo.deleteById(id);
	}
	
	public List<Socio> findAll(){
		return socioRepo.findAll();
	}
	
	public Socio findById(int id) {
		return socioRepo.findById(id).orElse(null);
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
	
}
