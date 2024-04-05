package es.rubengs.clubnautico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.rubengs.clubnautico.model.Socio;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/socios")
public class SocioRestController {
/*
	@Autowired
	SocioService socioService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Socio> findAll = socioService.findAll();
		if (findAll != null) {
			return ResponseEntity.ok(findAll);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salio mal");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
	
		Socio optfindById = socioService.findById(id);
			
				return ResponseEntity.ok(optfindById);
			
	}
	
	@PostMapping
	public ResponseEntity<?> createSocio(@RequestBody Socio socio) {
	    return socioService.createSocio(socio);
	}

	
	 @DeleteMapping("/{id}")
	    public ResponseEntity<?> deletePaciente(@PathVariable int id) {
	       	socioService.deleteSocio(id);
	    }

	 
	 @PutMapping("/{id}")
	    public ResponseEntity<?> updatePaciente(@RequestBody Socio socio) {
	        socioService.updateSocio(socio);
	    }
	*/
}
