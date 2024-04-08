package es.rubengs.clubnautico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rubengs.clubnautico.model.Salida;
import es.rubengs.clubnautico.repository.SalidaRepository;

@Service
public class SalidaService {

	@Autowired
	SalidaRepository salidaRepo;
	
	public Salida createSalida(Salida salida) {
		return salidaRepo.save(salida);
	}
	
	public void deleteSalida(int id) {
		salidaRepo.deleteById(id);
	}
	
	public List<Salida> findAll(){
		return salidaRepo.findAll();
	}
	
	public Salida findById(int id) {
		return salidaRepo.findById(id).orElse(null);
	}
	
	public Salida updateSalida(int id, Salida salidaDetails) {
        Optional<Salida> salidaOptional = salidaRepo.findById(id);
        if (salidaOptional.isPresent()) {
        	Salida existingSalida = salidaOptional.get();
            existingSalida.setCuota(salidaDetails.getCuota());
            existingSalida.setDestino(salidaDetails.getDestino());
            existingSalida.setFechaSalida(salidaDetails.getFechaSalida());
            existingSalida.setPatron(salidaDetails.getPatron());
            Salida updatedSalida = salidaRepo.save(existingSalida);
            return updatedSalida;
        } else {
            return null;
        }
    }
	
}

