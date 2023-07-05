package es.ufv.dis.ext.final2023.back.controller;


import es.ufv.dis.ext.final2023.back.dao.JsonDAO;
import es.ufv.dis.ext.final2023.back.model.Producto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(description = "Provee los datos de los Productos", name = "Controlador de Productos")
public class ProductoController {

    private JsonDAO jsonDAO = JsonDAO.getInstance();

    // GET
    @GetMapping("/Productos")
    @Operation(summary = "Devuelve una lista de todos los Productos",
            description = "Devuelve los datos pertinentes de los productos.")
    public List<Producto> productos_GET() {
        return jsonDAO.leerJsonProductos();
    }

    @GetMapping("/Productos/{categoria}")
    @Operation(summary = "Devuelve los productos por su categoría",
            description = "Devuelve los datos pertinentes del producto seleccionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK!"),
            @ApiResponse(responseCode = "404", description = "Not OK! - Not Found")
    })
    public static ResponseEntity<List<Producto>> productoCagegoria_GET(@PathVariable("categoria") String categoria) {
        try {
            // creamos una lista tipo Producto donde guardamos el json leído.
            List<Producto> listaProducto = new JsonDAO().leerJsonProductos();
            // creamos otra  lista para ir guardando los elementos que coincidan con la categoría introducida
            List<Producto> encontrado = new ArrayList<>();
            // recorremos la listaProducto en busca de los elementos que contengas la categoría introducida
            // y si lo encuentra los va agregando a la lista de encontrado
            for (Producto prod : listaProducto) {
                if (prod.getCategoria().equals(categoria)) {
                    encontrado.add(prod);
                }
            }
            // si la lista de encontrado no está vacía, significa que hay resultados y devuelve un OK
            if (!encontrado.isEmpty()) {
                return new ResponseEntity<>(encontrado, HttpStatus.OK);
            }
            // en caso contrario devuelve un Not Found
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
