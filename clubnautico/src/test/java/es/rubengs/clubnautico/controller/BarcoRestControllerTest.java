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

import es.rubengs.clubnautico.dto.BarcoDto;
import es.rubengs.clubnautico.dto.PatronDto;
import es.rubengs.clubnautico.service.BarcoService;

public class BarcoRestControllerTest {

	  @Mock
	    private BarcoService barcoService;

	    @InjectMocks
	    private BarcoRestController barcoRestController;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	
	    @Test
	    @Order(1)
	    public void testFindAll() {
	        List<BarcoDto> patronList = Arrays.asList(new BarcoDto(), new BarcoDto());
	        when(barcoService.findAll()).thenReturn(patronList);

	        ResponseEntity<?> responseEntity = barcoRestController.findAll();

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(patronList, responseEntity.getBody());
	    }

	    @Test
	    @Order(2)
	    public void testFindById() {
	        int id = 1;
	        BarcoDto barcoDto = new BarcoDto();
	        when(barcoService.findById(id)).thenReturn(barcoDto);

	        ResponseEntity<?> responseEntity = barcoRestController.findById(id);

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(barcoDto, responseEntity.getBody());
	    }

	    @Test
	    @Order(3)
	    public void testCreateBarco() {
	    	BarcoDto barcoDto = new BarcoDto();
	        when(barcoService.createBarco(barcoDto)).thenReturn(barcoDto);

	        BarcoDto createdBarco = barcoRestController.createBarco(barcoDto);

	        assertEquals(barcoDto, createdBarco);
	    }
	    
	    

	    @Test
	    @Order(5)
	    public void testDeleteBarco() {
	        int id = 1;

	        barcoRestController.deletePatron(id);

	        verify(barcoService).deleteBarco(id);
	    }

	    @Test
	    @Order(4)
	    public void testUpdateBarco() {
	        int id = 1;
	        BarcoDto barcoDetails = new BarcoDto();
	        BarcoDto updatedBarcoDto = new BarcoDto();
	        when(barcoService.updateBarco(id, barcoDetails)).thenReturn(updatedBarcoDto);

	        ResponseEntity<?> responseEntity = barcoRestController.updateBarco(id, barcoDetails);

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(updatedBarcoDto, responseEntity.getBody());
	    }
	    
}
