package Logica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListaSimple {

    private Nodo inicio;
    private int tamanio;

    public ListaSimple() {

        this.inicio = null;
        this.tamanio = 0;
    }

    public int getTamanio() {

        return this.tamanio;
    }

    public Nodo getNodo() {

        return this.inicio;
    }

    public boolean isEmpty(){

        boolean flag = false;

        if(this.inicio == null){

            flag = true;
        }
        return flag;
    }

    public void agregarAvenger(YoungAvenger avenger){

        Nodo nuevoNodo = new Nodo(avenger);

        if(isEmpty()){

            this.inicio = nuevoNodo;
        }else{

            nuevoNodo.setNext(this.inicio);
            this.inicio = nuevoNodo;
            this.tamanio++;
        }
    }

    public int buscarLineal(int codigoAvenger) {

        Nodo actual = this.inicio;

        while (actual != null) {

            if (actual.getAvenger().getCodigo() == codigoAvenger) {

                return actual.getAvenger().getCodigo();
            }

            actual = actual.getNext();
        }

        return -1; // Retorna -1 si no se encuentra
    }

    public void mostrarBusqueda(JTable table, int codigo){


        Nodo aux = this.inicio;

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Codigo", "Nombre", "Poder Especial", "Nivel de habilidad", "Mision Activa"}, 0);
        model.addRow(new Object[]{"Codigo", "Nombre", "Poder Especial", "Nivel de habilidad", "Mision Activa"});

        while(aux != null){

            if(codigo == aux.getAvenger().getCodigo()){

                model.addRow(new Object[]{aux.getAvenger().getCodigo(), aux.getAvenger().getNombre(), aux.getAvenger().getPoderEspecial(), aux.getAvenger().getNivelHabilidad(), aux.getAvenger().getMisionActiva()});
            }

            aux = aux.getNext();
        }
        table.setModel(model);
    }
    public void mostrar(JTable table){


        Nodo aux = this.inicio;

        while(aux != null){


            DefaultTableModel model = new DefaultTableModel(new Object[]{"Codigo", "Nombre", "Poder Especial", "Nivel de habilidad", "Mision Activa"}, 0);
            model.addRow(new Object[]{"Codigo", "Nombre", "Poder Especial", "Nivel de habilidad", "Mision Activa"});

            while(aux != null){

                model.addRow(new Object[]{aux.getAvenger().getCodigo(), aux.getAvenger().getNombre(), aux.getAvenger().getPoderEspecial(), aux.getAvenger().getNivelHabilidad(), aux.getAvenger().getMisionActiva()});
                aux = aux.getNext();
            }

            table.setModel(model);
        }
    }

    public void ordenar(){

        if (this.inicio == null || this.inicio.getNext() == null) return;

        boolean swapped;
        do {
            swapped = false;
            Nodo actual = this.inicio;
            Nodo siguiente = this.inicio.getNext();
            while (siguiente != null) {

                if (actual.getAvenger().getNivelHabilidad() > siguiente.getAvenger().getNivelHabilidad()) {

                    YoungAvenger temp = actual.getAvenger();
                    actual.setAvenger(siguiente.getAvenger());
                    siguiente.setAvenger(temp);
                    swapped = true;
                }
                actual = siguiente;
                siguiente = siguiente.getNext();
            }
        } while (swapped);
    }

    public ListaSimple filtrarLista(String poderEspecial){

        Nodo aux = this.inicio;
        ListaSimple lista = new ListaSimple();

        while(aux != null){

            if(!poderEspecial.equals(aux.getAvenger().getPoderEspecial())){

                lista.agregarAvenger(aux.getAvenger());
            }
            aux = aux.getNext();
        }

        return lista;
    }

    //Funcion recursiva
    public int contarElementos(ListaSimple listaSimple, int indice) {

        if(isEmpty()){

            return 0;
        }else{

            if (indice == this.tamanio + 1) {

                return 0;
            }

            return 1 + contarElementos(listaSimple, indice + 1);
        }
    }

    public ListaSimple filtrarConteo(String poderEspecial){

        Nodo aux = this.inicio;
        ListaSimple lista = new ListaSimple();

        while(aux != null){

            if(poderEspecial.equals(aux.getAvenger().getPoderEspecial())){

                lista.agregarAvenger(aux.getAvenger());
            }
            aux = aux.getNext();
        }

        return lista;
    }
}
