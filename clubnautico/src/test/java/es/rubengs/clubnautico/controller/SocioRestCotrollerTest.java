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
import es.rubengs.clubnautico.dto.SocioDto;
import es.rubengs.clubnautico.model.Socio;
import es.rubengs.clubnautico.service.BarcoService;
import es.rubengs.clubnautico.service.SocioService;

public class SocioRestCotrollerTest {

    @Mock
    private SocioService socioService;

    @Mock
    private BarcoService barcoService;

    @InjectMocks
    private SocioRestController socioRestController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    public void testFindAll() {
        List<SocioDto> socioList = Arrays.asList(new SocioDto(), new SocioDto());
        when(socioService.findAll()).thenReturn(socioList);

        ResponseEntity<?> responseEntity = socioRestController.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(socioList, responseEntity.getBody());
    }

    @Test
    @Order(2)
    public void testFindById() {
        int id = 1;
        SocioDto socioDto = new SocioDto();
        when(socioService.findById(id)).thenReturn(socioDto);

        ResponseEntity<?> responseEntity = socioRestController.findById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(socioDto, responseEntity.getBody());
    }

    @Test
    @Order(3)
    public void testCreateSocio() {
        SocioDto socioDto = new SocioDto();
        when(socioService.createSocio(socioDto)).thenReturn(socioDto);

        SocioDto createdSocio = socioRestController.createSocio(socioDto);

        assertEquals(socioDto, createdSocio);
    }

    @Test
    @Order(5)
    public void testDeleteSocio() {
        int id = 1;

        socioRestController.deleteSocio(id);

        verify(socioService).deleteSocio(id);
    }

    @Test
    @Order(4)
    public void testUpdateSocio() {
        int id = 1;
        Socio socioDetails = new Socio();
        Socio updatedSocio = new Socio();
        when(socioService.updateSocio(id, socioDetails)).thenReturn(updatedSocio);

        ResponseEntity<?> responseEntity = socioRestController.updateSocio(id, socioDetails);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedSocio, responseEntity.getBody());
    }

    @Test
    public void testAddBarcoToSocio() {
        int socioId = 1;
        BarcoDto barcoDto = new BarcoDto();
        when(barcoService.createBarcoWithSocio(socioId, barcoDto)).thenReturn(barcoDto);

        ResponseEntity<BarcoDto> responseEntity = socioRestController.addBarcoToSocio(socioId, barcoDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(barcoDto, responseEntity.getBody());
    }
}