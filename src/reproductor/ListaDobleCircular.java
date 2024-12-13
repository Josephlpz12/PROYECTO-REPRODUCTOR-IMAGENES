/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reproductor;

import java.util.Iterator;

/**
 *
 * @author jose
 */
public class ListaDobleCircular<T> implements Iterable<T> {
    private Nodo<T> actual;
    private Nodo<T> cabeza;

    public ListaDobleCircular() {
        this.cabeza = null;
        this.actual = null;
    }

    public boolean esVacia() {
        return cabeza == null;
    }

    public Nodo<T> getActual() {
        return actual;
    }
    
    /*
   
    public T ultima() {
    if (cabeza == null) {
        return null;
    }
    Nodo<T> nodo = cabeza;
    while (nodo.siguiente != cabeza) {
        nodo = nodo.siguiente;
    }
    return nodo.dato;
}*/
      public T ultima() {
        if (cabeza == null) {
            return null;
        }
        Nodo<T> nodo = cabeza;
        while (nodo.siguiente != cabeza) {
            nodo = nodo.siguiente;
        }
        actual = nodo; // Actualizar el nodo actual al último
        return nodo.dato;
    }

    
    

    public void agregar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            Nodo<T> ultimo = cabeza.anterior;
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
        }
    }

    public T siguiente() {
        if (actual == null) {
            actual = cabeza;
        } else {
            actual = actual.siguiente;
        }
        return actual.dato;
    }
    /*

    public T anterior() {
        if (actual != null) {
            actual = actual.anterior;
            return actual.dato;
        }
        return null;
    }
*/
    
     public T anterior() {
        if (cabeza == null || actual == null) {
            return null;
        }
        actual = actual.anterior;
        return actual.dato;
    }
     
     
    
    public int size() {
    if (cabeza == null) {
        return 0;
    }
    
    int size = 1;
    Nodo actual = cabeza.siguiente;
    while (actual != cabeza) {
        size++;
        actual = actual.siguiente;
    }
    return size;
}

    public void reset() {
        actual = cabeza;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListaDobleCircularIterator();
    }

    private class ListaDobleCircularIterator implements Iterator<T> {
        private Nodo<T> current = cabeza;
        private boolean firstPass = true;

        @Override
        public boolean hasNext() {
            return current != null && (firstPass || current != cabeza);
        }

        @Override
        public T next() {
            T dato = current.dato;
            current = current.siguiente;
            firstPass = false;
            return dato;
        }
    }

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
        Nodo<T> anterior;

        Nodo(T dato) {
            this.dato = dato;
        }
    }
}
