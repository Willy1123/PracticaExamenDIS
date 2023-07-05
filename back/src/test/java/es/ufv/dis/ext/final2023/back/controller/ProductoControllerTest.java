package es.ufv.dis.ext.final2023.back.controller;

import es.ufv.dis.ext.final2023.back.dao.JsonDAO;
import es.ufv.dis.ext.final2023.back.model.Producto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductoControllerTest {

    private ProductoController productoController;
    private JsonDAO jsonDAO = new JsonDAO();
    private PDFManager pdf;

    @Test
    void testCrearProducto() {
        // Arrange
        ProductoController controller = new ProductoController();
        Producto producto = new Producto();
        producto.setNombre("Nuevo Producto");
        producto.setCategoria("Categoría");
        producto.setPrecio(10);
        producto.setEAN13("1234567890123");

        // Act
        ResponseEntity<List<Producto>> response = controller.addProducto(producto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        List<Producto> productos = response.getBody();
        Assertions.assertNotNull(productos);

        Producto nuevoProducto = productos.get(productos.size() - 1);
        assertEquals("Nuevo Producto", nuevoProducto.getNombre());
        assertEquals("Categoría", nuevoProducto.getCategoria());
        assertEquals(10, nuevoProducto.getPrecio());
        assertEquals("1234567890123", nuevoProducto.getEAN13());
    }

    @Test
    public void testAñadirProductoAlInventario() {
        // Arrange
        List<Producto> productosExistentes = new ArrayList<>();
        Producto productoExistente = new Producto();
        productoExistente.setNombre("Producto Existente");
        productoExistente.setCategoria("Categoría Existente");
        productoExistente.setPrecio(19);
        productoExistente.setEAN13("9876543210987");
        productosExistentes.add(productoExistente);

        jsonDAO.guardarJsonZBS(productosExistentes);

        Producto productoNuevo = new Producto();
        productoNuevo.setNombre("Nuevo Producto");
        productoNuevo.setCategoria("Categoría");
        productoNuevo.setPrecio(10);
        productoNuevo.setEAN13("1234567890123");

        // Act
        ResponseEntity<List<Producto>> response = productoController.addProducto(productoNuevo);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        List<Producto> productos = response.getBody();
        //assertEquals(2, productos.size());
        Producto productoAñadido = productos.get(productos.size() - 1);
        assertEquals("Nuevo Producto", productoAñadido.getNombre());
        assertEquals("Categoría", productoAñadido.getCategoria());
        assertEquals(10.99, productoAñadido.getPrecio());
        assertEquals("1234567890123", productoAñadido.getEAN13());
    }

}