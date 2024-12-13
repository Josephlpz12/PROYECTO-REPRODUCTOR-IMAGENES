/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reproductor;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jose
 */
public class Album {
     private String nombre;
    private ListaDobleCircular<Foto> fotos;

    public Album(String nombre) {
        this.nombre = nombre;
        this.fotos = new ListaDobleCircular<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarFoto(Foto foto) {
        fotos.agregar(foto);
    }

    public ListaDobleCircular<Foto> getFotos() {
        return fotos;
    }
}
