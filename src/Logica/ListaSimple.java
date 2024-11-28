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

    /**
     * Función que verifica si la lista se encuentra vacía
     * @return regresa un booleano: false = no está vacía, true = está vacía
     */
    public boolean isEmpty(){

        boolean flag = false;

        if(this.inicio == null)
            flag = true;

        return flag;
    }

    /**
     * Método que agrega objetos a la lista
     * @param avenger se trata de una instancia de la clase YoungAvenger
     */
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

    /**
     * Método de busqueda de los objetos dentro de la lista, mediante el método de la burbuja
     * @param codigoAvenger se trata del filtro de la busqueda mediante el código asignado a un campo de la clase YoungAvenger
     * @return
     */
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

    /**
     * Metodo para mostrar los elementos de la lista
     * @param table es el objeto de Java Swing, donde será plasmada la información
     * @param codigo parametro para mostrar la información del elemento tras realizar una busqueda. Si no se requiere la busqueda asignar el 0
     * @param flag parametro para determinar si se trata de mostrar todos los elemntos de la lista (false) o solo el asignado por la busqueda (false).
     */
    public void mostrar(JTable table, int codigo, boolean flag){

        Nodo aux = this.inicio;

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Codigo", "Nombre", "Poder Especial", "Nivel de habilidad", "Mision Activa"}, 0);
        model.addRow(new Object[]{"Codigo", "Nombre", "Poder Especial", "Nivel de habilidad", "Mision Activa"});

        if(flag){

            while(aux != null){

                model.addRow(new Object[]{aux.getAvenger().getCodigo(), aux.getAvenger().getNombre(), aux.getAvenger().getPoderEspecial(), aux.getAvenger().getNivelHabilidad(), aux.getAvenger().getMisionActiva()});
                aux = aux.getNext();

            }

            table.setModel(model);
        }else{

            while(aux != null){

                if(codigo == aux.getAvenger().getCodigo()){

                    model.addRow(new Object[]{aux.getAvenger().getCodigo(), aux.getAvenger().getNombre(), aux.getAvenger().getPoderEspecial(), aux.getAvenger().getNivelHabilidad(), aux.getAvenger().getMisionActiva()});
                }

                aux = aux.getNext();
            }

            table.setModel(model);
        }
    }

    /**
     * Método que ordena la lista con el algoritmo de burbuja
     */
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


    //Funcion recursiva

    /**
     * Función recursiva para realizar el conteo de las misiones según el poder del Avenger
     * @param listaSimple aquí se introduce la lista que va a ser contada
     * @param indice se inicializa siempre en 0
     * @return retorna un entero que es la cantidad de misiones activas del Avenger según su poder
     */
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


    //True = realizar lista con coincidencias
    //False = generar lista con los objetos diferentes al filtro

    /**
     * Método para realizar un filtrado de la lista.
     * @param poderEspecial es un string que detalla porque poder especial se va realizar el filtro
     * @param flag es un boolean donde true provoca que la función haga la filtración por la coincidencias y false para que se realice por las no coicidencias por el filtro
     * @return retorna una lista con los filtros respectivos
     */
    public ListaSimple filtrar(String poderEspecial, boolean flag){

        Nodo aux = this.inicio;
        ListaSimple lista = new ListaSimple();

        if(flag){

            while(aux != null){

                if(poderEspecial.equals(aux.getAvenger().getPoderEspecial())){

                    lista.agregarAvenger(aux.getAvenger());
                }
                aux = aux.getNext();
            }
        }else{

            while(aux != null){

                if(!poderEspecial.equals(aux.getAvenger().getPoderEspecial())){

                    lista.agregarAvenger(aux.getAvenger());
                }
                aux = aux.getNext();
            }
        }

        return lista;
    }
}
