package es.ufv.dis.ext.final2023.back.controller;


import es.ufv.dis.ext.final2023.back.dao.JsonDAO;
import es.ufv.dis.ext.final2023.back.model.Producto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

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

}
