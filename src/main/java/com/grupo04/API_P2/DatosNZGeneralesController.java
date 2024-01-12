package com.grupo04.API_P2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

    @DeleteMapping("/datosGenerales/{id}")
    public ResponseEntity<String> deleteRegistro(@PathVariable String id) {
        DataHandling dataHandling = new DataHandling();
        boolean success = dataHandling.deleteRegistro(id);

        if (success) {
            return ResponseEntity.ok("Registro eliminado correctamente");
        } else {
            return ResponseEntity.status(500).body("Error al eliminar el registro");
        }
    }

    @PutMapping("/datosGenerales/{id}")
    public ResponseEntity<String> updateRegistro(@PathVariable String id, @RequestBody DatosNuevaZelanda updatedRegistro) {
        DataHandling dataHandling = new DataHandling();

        // Asignar el ID proporcionado al registro actualizado
        updatedRegistro.setId(id);

        boolean success = dataHandling.updateRegistro(updatedRegistro);

        if (success) {
            return ResponseEntity.ok("Registro actualizado correctamente");
        } else {
            return ResponseEntity.status(500).body("Error al actualizar el registro");
        }
    }

    @PostMapping("/datosGenerales")
    public ResponseEntity<String> createRegistro(@RequestBody DatosNuevaZelanda nuevoRegistro) {
        DataHandling dataHandling = new DataHandling();

        // Asignar un nuevo ID al registro antes de guardarlo
        nuevoRegistro.setId( UUID.randomUUID().toString());

        boolean success = dataHandling.addRegistro(nuevoRegistro);

        if (success) {
            return ResponseEntity.ok("Registro creado correctamente");
        } else {
            return ResponseEntity.status(500).body("Error al crear el registro");
        }
    }

    @GetMapping("/msCodes")
    public ResponseEntity<List<String>> getMsCodes() {
        DataHandling dataHandling = new DataHandling();

        List<String> msCodes = dataHandling.getMsCodes();

        if (!msCodes.isEmpty()) {
            return ResponseEntity.ok(msCodes);
        } else {
            return ResponseEntity.status(404).body(null);  // No se encontraron MsCodes
        }
    }

}
