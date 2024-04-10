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
import es.rubengs.clubnautico.dto.SocioDto;
import es.rubengs.clubnautico.model.Socio;
import es.rubengs.clubnautico.service.BarcoService;
import es.rubengs.clubnautico.service.SocioService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/socios")
public class SocioRestController {

	@Autowired
	SocioService socioService;
	@Autowired
	BarcoService barcoService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<SocioDto> findAll = socioService.findAll();
		if (findAll != null) {
			return ResponseEntity.ok(findAll);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salio mal");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {

		SocioDto optfindById = socioService.findById(id);

		return ResponseEntity.ok(optfindById);

	}

	@PostMapping
	@ResponseBody
	public SocioDto createSocio(@RequestBody SocioDto socio) {
		return socioService.createSocio(socio);
	}

	@DeleteMapping("/{id}")
	public void deleteSocio(@PathVariable int id) {
		socioService.deleteSocio(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateSocio(@PathVariable int id, @RequestBody Socio socioDetails) {
		Socio updatedSocio = socioService.updateSocio(id, socioDetails);
		if (updatedSocio != null) {
			return ResponseEntity.ok(updatedSocio);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Socio no encontrado con ID: " + id);
		}
	}

	@PostMapping("/{id}/barcos")
	public ResponseEntity<BarcoDto> addBarcoToSocio(@PathVariable(value = "id") Integer socioId,
			@RequestBody BarcoDto barcoDto) {
		BarcoDto nuevoBarco = barcoService.createBarcoWithSocio(socioId, barcoDto);
		if (nuevoBarco != null) {
			return new ResponseEntity<>(nuevoBarco, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
