package es.ufv.dis.ext.final2023.back.controller;

import es.ufv.dis.ext.final2023.back.model.Producto;

import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

public class PDFManager {

    /**
     * Escribir/sobreescribir archivo PDF que contiene el backup
     * @param productoList Lista de Objetos (Producto) que guardar
     * @since v0.3
     */
    public void generarPDF(List<Producto> productoList){
        try {
            Document doc = new Document(PageSize.A4, 50, 50, 100, 72);
            // la ruta ./Productos/backup.pdf es la raiz del proyecto
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.dir") + "/Productos/backup.pdf"));
            doc.open();
            // Convertimos la lista en un String con formato JSON
            String text = new Gson().toJson(productoList);
            Paragraph p = new Paragraph(text);
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            doc.add(p);
            doc.close();
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
