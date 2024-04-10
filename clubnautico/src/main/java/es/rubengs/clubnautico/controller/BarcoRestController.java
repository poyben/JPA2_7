package es.rubengs.clubnautico.controller;

import java.util.List;
import java.util.Map;

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

import es.rubengs.clubnautico.dto.BarcoDto;
import es.rubengs.clubnautico.repository.SocioRepository;
import es.rubengs.clubnautico.service.BarcoService;
import es.rubengs.clubnautico.service.SocioService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/barcos")
public class BarcoRestController {

	@Autowired
	BarcoService barcoService;
	@Autowired
	SocioRepository socioRepository;
	@Autowired
	SocioService socioService;

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
	public BarcoDto createBarco(@RequestBody BarcoDto barcoDto) {
		return barcoService.createBarco(barcoDto);
	}

	@PostMapping("{socioId}")
	@ResponseBody
	public BarcoDto createBarco(@PathVariable int socioId,@RequestBody BarcoDto barcoDto) {
		//SocioDto socioDto = socioService.convertToDto(socioRepository.findById(socioId));
		//barcoDto.setSocioDto(socioDto);
		return barcoService.createBarcoWithSocio(socioId, barcoDto);
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
	
	
	@PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateBarco(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        BarcoDto updatedBarcoDto = barcoService.partialUpdateBarco(id, updates);
        if (updatedBarcoDto != null) {
            return ResponseEntity.ok(updatedBarcoDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Barco no encontrado con ID: " + id);
        }
    }
	

}
