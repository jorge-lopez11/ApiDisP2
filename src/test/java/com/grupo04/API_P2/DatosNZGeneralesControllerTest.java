package com.grupo04.API_P2;

import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DatosNZGeneralesControllerTest {

    DatosNuevaZelanda datoValido = new DatosNuevaZelanda("ab0ffaa8-4cf7-4e8c-89b7-6fc4659142f8","MEASA", "2007", "_Proportion", " 14", " 1.3", " 12.7", " 15.4", "null");
    @Test
    public void getById() {
        // Definir la URL de la API
        String apiUrl = "http://localhost:8090/datosGenerales/ab0ffaa8-4cf7-4e8c-89b7-6fc4659142f8";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DatosNuevaZelanda> response = restTemplate.getForEntity(apiUrl, DatosNuevaZelanda.class);
        // Verificar el código de respuesta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(datoValido.ID, response.getBody().ID);
        assertEquals(datoValido.MsCode, response.getBody().MsCode);
        assertEquals(datoValido.EstCode, response.getBody().EstCode);
        assertEquals(datoValido.Estimate, response.getBody().Estimate);
        assertEquals(datoValido.LowerCIB, response.getBody().LowerCIB);
        assertEquals(datoValido.UpperCIB, response.getBody().UpperCIB);
        assertEquals(datoValido.Flag, response.getBody().Flag);
        assertEquals(datoValido.SE, response.getBody().SE);
        assertEquals(datoValido.getYear(), response.getBody().getYear());
    }

    @Test
    public void getByIdReturnNull() {
        // Definir la URL de la API
        String apiUrl = "http://localhost:8090/datosGenerales/idInventadoQueNoExiste____";
        RestTemplate restTemplate = new RestTemplate();

        DatosNuevaZelanda registroVacio = new DatosNuevaZelanda();
        ResponseEntity<DatosNuevaZelanda> response = restTemplate.getForEntity(apiUrl, DatosNuevaZelanda.class);


        // Verificar el código de respuesta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(registroVacio.ID, response.getBody());
        assertEquals(registroVacio.MsCode, response.getBody());
        assertEquals(registroVacio.EstCode, response.getBody());
        assertEquals(registroVacio.Estimate, response.getBody());
        assertEquals(registroVacio.LowerCIB, response.getBody());
        assertEquals(registroVacio.UpperCIB, response.getBody());
        assertEquals(registroVacio.Flag, response.getBody());
        assertEquals(registroVacio.SE, response.getBody());
        assertEquals(registroVacio.getYear(), response.getBody());
    }

    @Test
    public void createRegistro(){
        String apiURL = "http://localhost:8090/datosGenerales";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        DatosNuevaZelanda nuevoRegistro = new DatosNuevaZelanda();
        nuevoRegistro.MsCode = "MEASA";
        nuevoRegistro.Year = "2023";
        nuevoRegistro.EstCode = "_Proportion";
        nuevoRegistro.Estimate = "99999999";
        nuevoRegistro.Flag = "Hola Jorge";
        nuevoRegistro.SE = "99";
        nuevoRegistro.LowerCIB = "3.3";
        nuevoRegistro.UpperCIB = "10.0";

        HttpEntity<DatosNuevaZelanda> requestEntity = new HttpEntity<>(nuevoRegistro, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(apiURL, requestEntity, String.class);
        String idResponse = response.getBody().toString();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        apiURL = "http://localhost:8090/datosGenerales/" + idResponse;
        ResponseEntity<DatosNuevaZelanda> responseGet = restTemplate.getForEntity(apiURL, DatosNuevaZelanda.class);
        assertEquals(HttpStatus.OK, responseGet.getStatusCode());
        assertEquals(idResponse, responseGet.getBody().ID);
        assertEquals(nuevoRegistro.MsCode, responseGet.getBody().MsCode);
        assertEquals(nuevoRegistro.EstCode, responseGet.getBody().EstCode);
        assertEquals(nuevoRegistro.Estimate, responseGet.getBody().Estimate);
        assertEquals(nuevoRegistro.LowerCIB, responseGet.getBody().LowerCIB);
        assertEquals(nuevoRegistro.UpperCIB, responseGet.getBody().UpperCIB);
        assertEquals(nuevoRegistro.Flag, responseGet.getBody().Flag);
        assertEquals(nuevoRegistro.SE, responseGet.getBody().SE);
        assertEquals(nuevoRegistro.getYear(), responseGet.getBody().getYear());
    }

    @Test
    public void updateRegistro(){
        DatosNuevaZelanda nuevoRegistro = new DatosNuevaZelanda();
        nuevoRegistro.MsCode = "MEASA";
        nuevoRegistro.Year = "2023";
        nuevoRegistro.EstCode = "_Proportion";
        nuevoRegistro.Estimate = "99999999";
        nuevoRegistro.Flag = "Hola Jorge";
        nuevoRegistro.SE = "99";
        nuevoRegistro.LowerCIB = "3.3";
        nuevoRegistro.UpperCIB = "10.0";
        nuevoRegistro.ID = "c53b1dc6-58a1-4d2f-aa64-7d87828746a4";

        String apiURL = "http://localhost:8090/datosGenerales/c53b1dc6-58a1-4d2f-aa64-7d87828746a4";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DatosNuevaZelanda> requestEntity = new HttpEntity<>(nuevoRegistro, headers);

        restTemplate.put(apiURL, requestEntity);

        ResponseEntity<DatosNuevaZelanda> response = restTemplate.getForEntity(apiURL, DatosNuevaZelanda.class);
        // Verificar el código de respuesta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(nuevoRegistro.ID, response.getBody().ID);
        assertEquals(nuevoRegistro.MsCode, response.getBody().MsCode);
        assertEquals(nuevoRegistro.EstCode, response.getBody().EstCode);
        assertEquals(nuevoRegistro.Estimate, response.getBody().Estimate);
        assertEquals(nuevoRegistro.LowerCIB, response.getBody().LowerCIB);
        assertEquals(nuevoRegistro.UpperCIB, response.getBody().UpperCIB);
        assertEquals(nuevoRegistro.Flag, response.getBody().Flag);
        assertEquals(nuevoRegistro.SE, response.getBody().SE);
        assertEquals(nuevoRegistro.getYear(), response.getBody().getYear());
    }

    @Test
    public void deleteRegistro(){
        DatosNuevaZelanda registroVacio = new DatosNuevaZelanda();

        String apiURL = "http://localhost:8090/datosGenerales/fcf56a66-2acf-4536-a418-328bdc2f45cd";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(apiURL);

        ResponseEntity<DatosNuevaZelanda> response = restTemplate.getForEntity(apiURL, DatosNuevaZelanda.class);

        // Verificar el código de respuesta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(registroVacio.ID, response.getBody());
        assertEquals(registroVacio.MsCode, response.getBody());
        assertEquals(registroVacio.EstCode, response.getBody());
        assertEquals(registroVacio.Estimate, response.getBody());
        assertEquals(registroVacio.LowerCIB, response.getBody());
        assertEquals(registroVacio.UpperCIB, response.getBody());
        assertEquals(registroVacio.Flag, response.getBody());
        assertEquals(registroVacio.SE, response.getBody());
        assertEquals(registroVacio.getYear(), response.getBody());

    }
}