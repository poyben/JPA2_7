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

import es.rubengs.clubnautico.dto.BarcoDto;
import es.rubengs.clubnautico.model.Barco;
import es.rubengs.clubnautico.service.BarcoService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/barcos")
public class BarcoRestController {

	@Autowired
	BarcoService barcoService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<BarcoDto> findAll = barcoService.findAll();
		if (findAll != null) {
			return ResponseEntity.ok(findAll);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salio mal");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
	
		BarcoDto optfindById = barcoService.findById(id);
			
				return ResponseEntity.ok(optfindById);
			
	}
	
	@PostMapping
	@ResponseBody
	public Barco createBarco(@RequestBody Barco barco) {
	    return barcoService.createBarco(barco);
	}

	@DeleteMapping("/{id}")
    public void deletePatron(@PathVariable int id) {
       	barcoService.deleteBarco(id);
    }
	
	@PutMapping("/{id}")
	 public ResponseEntity<?> updateBarco(@PathVariable int id, @RequestBody BarcoDto barcoDetails) {
		 BarcoDto updatedBarco = barcoService.updateBarco(id, barcoDetails);
		    if (updatedBarco != null) {
		        return ResponseEntity.ok(updatedBarco);
		    } else {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Barco no encontrado con ID: " + id);
		    }
		}
	
}
