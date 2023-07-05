package es.ufv.dis.ext.final2023.back.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import es.ufv.dis.ext.final2023.back.model.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonDAO extends ArrayList<Producto> {

    private static JsonDAO jsonDAO;

    // Para comprobar si hay instancias creadas (Método Singleton)
    public static JsonDAO getInstance() {
        if (jsonDAO == null) {
            jsonDAO = new JsonDAO();
        }
        return jsonDAO;
    }

    public List<Producto> leerJsonProductos() {

        try (
                // leermo el fichero que le pasemos dentro de "resources" y lo carga en un reader
                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("template.json"))))
        ) {

            // convertimos el reader en un objeto JSON
            List<Producto> listaProducto = new Gson().fromJson(reader, new TypeToken<List<Producto>>()
            {}.getType());

            // devuelve la lista de "data" del json
            System.out.println("Elementos de la lista: " + listaProducto.toArray().length);
            return listaProducto;
        } catch (Exception ex) {
            System.err.println("Error al leer el fichero!");
            ex.printStackTrace();
            // si no se ha leído nada, devuelve un array vacío
            return null;
        }
    }

    public void guardarJsonZBS(List<Producto> nuevoProd) {
        try (
                BufferedWriter output = new BufferedWriter(new FileWriter("./src/main/resources/template.json", false))
        ) {
            List<Producto> dataZBS = new ArrayList<>(nuevoProd);
            new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(dataZBS, output);

        } catch (IOException e) {
            System.err.println("Fallo al escribir en el Json ZonasBásicas Salud");
            throw new RuntimeException(e);
        }


    }
}
