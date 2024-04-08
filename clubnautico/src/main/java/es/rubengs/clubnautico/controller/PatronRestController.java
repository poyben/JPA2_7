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

import es.rubengs.clubnautico.dto.PatronDto;
import es.rubengs.clubnautico.model.Patron;
import es.rubengs.clubnautico.model.Socio;
import es.rubengs.clubnautico.service.PatronService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/patrones")
public class PatronRestController {

	@Autowired
	PatronService patronService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<PatronDto> findAll = patronService.findAllDTOs();
		if (findAll != null) {
			return ResponseEntity.ok(findAll);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salio mal");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
	
		PatronDto optfindById = patronService.findByIdDTO(id);
			
				return ResponseEntity.ok(optfindById);
			
	}
	
	@PostMapping
	@ResponseBody
	public PatronDto createPatron(@RequestBody PatronDto patron) {
	    return patronService.createPatron(patron);
	}

	
	 @DeleteMapping("/{id}")
	    public void deletePatron(@PathVariable int id) {
	       	patronService.deletePatron(id);
	    }

	 
	 @PutMapping("/{id}")
	 public ResponseEntity<?> updatePatron(@PathVariable int id, @RequestBody PatronDto patronDetails) {
		 PatronDto updatedPatron = patronService.updatePatron(id, patronDetails);
		    if (updatedPatron != null) {
		        return ResponseEntity.ok(updatedPatron);
		    } else {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patron no encontrado con ID: " + id);
		    }
		}
	
}
