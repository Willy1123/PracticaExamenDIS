package es.ufv.dis.ext.final2023.back.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Producto {
    private String nombre;
    private String categoria;
    private int precio;
    private String EAN13;
}
