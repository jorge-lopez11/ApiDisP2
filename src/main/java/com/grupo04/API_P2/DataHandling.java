package com.grupo04.API_P2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class DataHandling {
    User getUserInfo (String name){
        User foundUser = null;
        JSONReader reader = new JSONReader();

        ArrayList<User> usersList = reader.readJsonFile("./src/main/resources/users.json");
        for (User user : usersList){
            if (user.getName().equalsIgnoreCase(name)){
                foundUser = user;
            }
        }
        return foundUser;
    }

    ArrayList<User> addUser(User newUser){
        JSONReader reader = new JSONReader();
        ArrayList<User> userList = reader.readJsonFile("./src/main/resources/users.json");
        userList.add(newUser);
        reader.writeJson("./src/main/resources/users.json", userList);
        return userList;
    }

    DatosNuevaZelanda getRegistro (String id){
        DatosNuevaZelanda registroEncontrado = null;
        JSONReader reader = new JSONReader();

        ArrayList<DatosNuevaZelanda> registros = reader.readDatosGenerales("./src/main/resources/cp-national-datafile.json");
        for (DatosNuevaZelanda registro : registros){
            if (registro.getId().equals(id)){
                registroEncontrado = registro;
            }
        }
        return registroEncontrado;
    }

    ArrayList<DatosNuevaZelanda> getRegistroPorMsCode (String mscode){
        DatosNuevaZelanda registroEncontrado = null;
        JSONReader reader = new JSONReader();

        ArrayList<DatosNuevaZelanda> registrosEncontrados = new ArrayList<>();

        ArrayList<DatosNuevaZelanda> registros = reader.readDatosGenerales("./src/main/resources/cp-national-datafile.json");
        for (DatosNuevaZelanda registro : registros){
            if (registro.getMsCode().equals(mscode)){
                registrosEncontrados.add(registro);
            }
        }
        return registrosEncontrados;
    }

    boolean deleteRegistro(String id) {
        JSONReader reader = new JSONReader();
        ArrayList<DatosNuevaZelanda> registros = reader.readDatosGenerales("./src/main/resources/cp-national-datafile.json");

        boolean registroEncontrado = false;

        for (DatosNuevaZelanda registro : registros) {
            if (registro.getId().equals(id)) {
                registros.remove(registro);
                registroEncontrado = true;
                break;  // Sale del bucle después de eliminar el primer registro coincidente
            }
        }
        if (registroEncontrado) {
            return reader.writeJsonDatosGenerales("./src/main/resources/cp-national-datafile.json", registros);
        } else {
            return false;  // El registro no fue encontrado
        }
    }

    boolean updateRegistro(DatosNuevaZelanda updatedRegistro) {
        JSONReader reader = new JSONReader();
        ArrayList<DatosNuevaZelanda> registros = reader.readDatosGenerales("./src/main/resources/cp-national-datafile.json");

        for (int i = 0; i < registros.size(); i++) {
            DatosNuevaZelanda registro = registros.get(i);
            if (registro.getId().equals(updatedRegistro.getId())) {
                registros.set(i, updatedRegistro);
                break;  // Sale del bucle después de actualizar el primer registro coincidente
            }
        }

        return reader.writeJsonDatosGenerales("./src/main/resources/cp-national-datafile.json", registros);
    }
    boolean addRegistro(DatosNuevaZelanda nuevoRegistro) {
        JSONReader reader = new JSONReader();
        ArrayList<DatosNuevaZelanda> registros = reader.readDatosGenerales("./src/main/resources/cp-national-datafile.json");

        // Asignar un nuevo ID al registro antes de agregarlo
        nuevoRegistro.setId(UUID.randomUUID().toString());

        registros.add(nuevoRegistro);

        return reader.writeJsonDatosGenerales("./src/main/resources/cp-national-datafile.json", registros);
    }
    List<String> getMsCodes() {
        JSONReader reader = new JSONReader();
        ArrayList<DatosNuevaZelanda> registros = reader.readDatosGenerales("src/main/resources/cp-national-datafile.json");

        List<String> distinctMsCodes = new ArrayList<>();

        for (DatosNuevaZelanda registro : registros) {
            String msCode = registro.getMsCode();
            if (!distinctMsCodes.contains(msCode)) {
                distinctMsCodes.add(msCode);
            }
        }
        return distinctMsCodes;
    }
    /*DatosAgrupados getPorMscode (String mscode){
        DatosAgrupados registroEncontrado = null;
        JSONReader reader = new JSONReader();

        ArrayList<DatosAgrupados> registros = reader.readDatosAgrupados("./src/main/resources/MsCode_json.json");
        for (DatosAgrupados registro : registros){
            if (registro.getMsCode().equals(mscode)){
                registroEncontrado = registro;
            }
        }
        return registroEncontrado;
    }*/
}
