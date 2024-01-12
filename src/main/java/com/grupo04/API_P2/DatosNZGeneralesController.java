package com.grupo04.API_P2;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class DatosNZGeneralesController {

    @GetMapping("/datosGenerales")
    public ArrayList<DatosNuevaZelanda> users(@RequestParam(value = "name", defaultValue = "World") String name) {
        JSONReader reader = new JSONReader();
        ArrayList<DatosNuevaZelanda> datosGenerales = reader.readDatosGenerales("src/main/resources/cp-national-datafile.json");
        return datosGenerales;
    }

}