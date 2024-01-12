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
}
