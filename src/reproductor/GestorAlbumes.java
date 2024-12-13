/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reproductor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jose
 */
public class GestorAlbumes {
     public static List<Album> albumes;

    public GestorAlbumes() {
        albumes = new ArrayList<>();
    }

    public void agregarAlbum(Album album) {
        albumes.add(album);
    }

    public List<Album> getAlbumes() {
        return albumes;
    }

    /*
      public void agregarAlbum(Album album) {
        albumes.add(album);
    }
    */

    public Album getAlbum(String nombre) {
        for (Album album : albumes) {
            if (album.getNombre().equals(nombre)) {
                return album;
            }
        }
        return null;
    }

   
    
    public void guardarEnXML(String filePath) {
    try {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // Crear documento XML
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("albumes");
        doc.appendChild(rootElement);

        for (Album album : albumes) {
            Element albumElement = doc.createElement("album");
            rootElement.appendChild(albumElement);

            // Nombre del álbum
            Element nombreElement = doc.createElement("nombre");
            nombreElement.appendChild(doc.createTextNode(album.getNombre()));
            albumElement.appendChild(nombreElement);

              // Cantidad de imágenes en el álbum
            Element cantidadElement = doc.createElement("cantidad");
            cantidadElement.appendChild(doc.createTextNode(String.valueOf(album.getFotos().size())));
            albumElement.appendChild(cantidadElement);
            // Elemento fotos
            Element fotosElement = doc.createElement("fotos");
            albumElement.appendChild(fotosElement);

            // Rutas de las fotos
            for (Foto foto : album.getFotos()) {
                Element rutaElement = doc.createElement("ruta");
                rutaElement.appendChild(doc.createTextNode(foto.getRuta()));
                fotosElement.appendChild(rutaElement);
            }
        }

        // Guardar el contenido en el archivo XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Para indentar el XMl
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));

        transformer.transform(source, result);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al guardar el archivo XML: " + e.getMessage());
    }
}
    
}
