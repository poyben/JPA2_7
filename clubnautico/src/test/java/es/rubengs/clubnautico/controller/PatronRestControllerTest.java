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

import es.rubengs.clubnautico.dto.PatronDto;
import es.rubengs.clubnautico.service.PatronService;

public class PatronRestControllerTest {

	  @Mock
	    private PatronService patronService;

	    @InjectMocks
	    private PatronRestController patronRestController;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    @Order(1)
	    public void testFindAll() {
	        List<PatronDto> patronList = Arrays.asList(new PatronDto(), new PatronDto());
	        when(patronService.findAllDTOs()).thenReturn(patronList);

	        ResponseEntity<?> responseEntity = patronRestController.findAll();

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(patronList, responseEntity.getBody());
	    }

	    @Test
	    @Order(2)
	    public void testFindById() {
	        int id = 1;
	        PatronDto patronDto = new PatronDto();
	        when(patronService.findByIdDTO(id)).thenReturn(patronDto);

	        ResponseEntity<?> responseEntity = patronRestController.findById(id);

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(patronDto, responseEntity.getBody());
	    }

	    @Test
	    @Order(3)
	    public void testCreatePatron() {
	        PatronDto patronDto = new PatronDto();
	        when(patronService.createPatron(patronDto)).thenReturn(patronDto);

	        PatronDto createdPatron = patronRestController.createPatron(patronDto);

	        assertEquals(patronDto, createdPatron);
	    }

	    @Test
	    @Order(5)
	    public void testDeletePatron() {
	        int id = 1;

	        patronRestController.deletePatron(id);

	        verify(patronService).deletePatron(id);
	    }

	    @Test
	    @Order(4)
	    public void testUpdatePatron() {
	        int id = 1;
	        PatronDto patronDetails = new PatronDto();
	        PatronDto updatedPatronDto = new PatronDto();
	        when(patronService.updatePatron(id, patronDetails)).thenReturn(updatedPatronDto);

	        ResponseEntity<?> responseEntity = patronRestController.updatePatron(id, patronDetails);

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(updatedPatronDto, responseEntity.getBody());
	    }
	}