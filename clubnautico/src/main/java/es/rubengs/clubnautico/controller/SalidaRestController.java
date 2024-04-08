package es.rubengs.clubnautico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.rubengs.clubnautico.model.Salida;
import es.rubengs.clubnautico.model.Socio;
import es.rubengs.clubnautico.service.SalidaService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/salidas")
public class SalidaRestController {

	@Autowired
	SalidaService salidaService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Salida> findAll = salidaService.findAll();
		if (findAll != null) {
			return ResponseEntity.ok(findAll);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salio mal");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
	
		Salida optfindById = salidaService.findById(id);
			
				return ResponseEntity.ok(optfindById);
			
	}
	
	@PostMapping
	@ResponseBody
	public Salida createSalida(@RequestBody Salida salida) {
	    return salidaService.createSalida(salida);
	}

	
	 @DeleteMapping("/{id}")
	    public void deleteSalida(@PathVariable int id) {
	       	salidaService.deleteSalida(id);
	    }

	 
	 @PutMapping("/{id}")
	 public ResponseEntity<?> updateSalida(@PathVariable int id, @RequestBody Salida salidaDetails) {
		    Salida updatedSalida = salidaService.updateSalida(id, salidaDetails);
		    if (updatedSalida != null) {
		        return ResponseEntity.ok(updatedSalida);
		    } else {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Saldia no encontrado con ID: " + id);
		    }
		}
	
}
