package es.rubengs.clubnautico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.rubengs.clubnautico.dto.SalidaDto;
import es.rubengs.clubnautico.service.SalidaService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/salidas")
public class SalidaRestController {

	@Autowired
	SalidaService salidaService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<SalidaDto> findAll = salidaService.findAllDtos();
		if (findAll != null) {
			return ResponseEntity.ok(findAll);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salio mal");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {

		SalidaDto optfindById = salidaService.findByIdDto(id);

		return ResponseEntity.ok(optfindById);

	}

	/*
	@PostMapping
	@ResponseBody
	public SalidaDto createSalida(@RequestBody SalidaDto salida) {
		return salidaService.createSalida(salida);
	}
	*/
	
	@PostMapping("/{barcoId}/{patronId}")
	@ResponseBody
	public SalidaDto createSalida(@PathVariable int barcoId, @PathVariable int patronId, @RequestBody SalidaDto salidaDto) {
	    return salidaService.createSalida(barcoId, patronId, salidaDto);
	}


	@DeleteMapping("/{id}")
	public void deleteSalida(@PathVariable int id) {
		salidaService.deleteSalida(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateSalida(@PathVariable int id, @RequestBody SalidaDto salidaDetails) {
		SalidaDto updatedSalida = salidaService.updateSalida(id, salidaDetails);
		if (updatedSalida != null) {
			return ResponseEntity.ok(updatedSalida);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Saldia no encontrado con ID: " + id);
		}
	}

	@PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateSalida(@PathVariable int id, @RequestBody SalidaDto updates) {
        SalidaDto updatedSalida = salidaService.partialUpdateSalida(id, updates);
        if (updatedSalida != null) {
            return ResponseEntity.ok(updatedSalida);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salida no encontrada con ID: " + id);
        }
    }
	
}
