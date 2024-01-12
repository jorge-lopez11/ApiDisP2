package com.grupo04.API_P2;

import java.util.ArrayList;
import java.util.UUID;

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

    DatosNuevaZelanda getRegistro (UUID id){
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
}
