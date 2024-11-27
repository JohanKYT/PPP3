package Logica;

public class Nodo {

    private YoungAvenger avenger;
    private Nodo next;

    public Nodo(YoungAvenger avenger) {

        this.avenger = avenger;
        this.next = null;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    public YoungAvenger getAvenger() {
        return avenger;
    }

    public void setAvenger(YoungAvenger avenger) {
        this.avenger = avenger;
    }
}
