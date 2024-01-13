package com.grupo04.API_P2;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class DatosAgrupadosController {

    /*@GetMapping("/buscarPorMsCode/{msCode}")
    public ResponseEntity<DatosAgrupados> getByMscode(@PathVariable String msCode){
        DataHandling dataHandling = new DataHandling();
        DatosNuevaZelanda registro = dataHandling.getRegistro(msCode);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }*/
    @GetMapping("/datosAgrupados")
    public DatosAgrupados getDatosAgrupados() {
        JSONReader reader = new JSONReader();
        DatosAgrupados datosAgrupados = reader.readDatosAgrupados("src/main/resources/MsCode_json.json");
        return datosAgrupados;
    }

    @GetMapping("/datosAgrupados/{mscode}")
    public ArrayList<DatosNuevaZelanda> getByMsCode(@PathVariable String mscode){
        DataHandling dataHandling = new DataHandling();
        ArrayList<DatosNuevaZelanda> registrosAgrupados = dataHandling.getRegistroPorMsCode(mscode);
        return registrosAgrupados;
    }

}
