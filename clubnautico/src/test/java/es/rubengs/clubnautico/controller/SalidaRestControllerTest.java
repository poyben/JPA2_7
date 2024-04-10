package es.rubengs.clubnautico.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.rubengs.clubnautico.dto.SalidaDto;
import es.rubengs.clubnautico.service.SalidaService;

public class SalidaRestControllerTest {

	@Mock
	private SalidaService salidaService;

	@InjectMocks
	private SalidaRestController salidaRestController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@Order(1)
	public void testFindAll() {
		List<SalidaDto> salidaList = Arrays.asList(new SalidaDto(), new SalidaDto());
		when(salidaService.findAllDtos()).thenReturn(salidaList);

		ResponseEntity<?> responseEntity = salidaRestController.findAll();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(salidaList, responseEntity.getBody());
	}

	@Test
	@Order(2)
	public void testFindById() {
		int id = 1;
		SalidaDto salidaDto = new SalidaDto();
		when(salidaService.findByIdDto(id)).thenReturn(salidaDto);

		ResponseEntity<?> responseEntity = salidaRestController.findById(id);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(salidaDto, responseEntity.getBody());
	}

	@Test
	@Order(3)
	public void testCreateSalida() {
		SalidaDto salidaDto = new SalidaDto();
		int barcoId = 1;
		int patronId = 2;
		when(salidaService.createSalida(barcoId, patronId, salidaDto)).thenReturn(salidaDto);
		SalidaDto createdSalida = salidaRestController.createSalida(barcoId, patronId, salidaDto);
		assertEquals(salidaDto, createdSalida);
	}

	@Test
	@Order(5)
	public void testDeleteSalida() {
		int id = 1;

		salidaRestController.deleteSalida(id);

		verify(salidaService).deleteSalida(id);
	}

	@Test
	@Order(4)
	public void testUpdateSalida() {
		int id = 1;
		SalidaDto salidaDetails = new SalidaDto();
		SalidaDto updatedSalida = new SalidaDto();
		when(salidaService.updateSalida(id, salidaDetails)).thenReturn(updatedSalida);

		ResponseEntity<?> responseEntity = salidaRestController.updateSalida(id, salidaDetails);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(updatedSalida, responseEntity.getBody());
	}
}