package GUI;

import Logica.ListaSimple;
import Logica.YoungAvenger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIPrueba {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField codigo;
    private JTextField nombre;
    private JComboBox poderEspecial;
    private JComboBox nivelHabilidad;
    private JComboBox misionActiva;
    private JButton agregarButton;
    private JButton limpiarButton;
    private JPanel GUIPrueba;
    private JTable table1;
    private JButton generarTablaButton;
    private JTextField codigoBuscar;
    private JButton buscarButton;
    private JTable table2;
    private JComboBox comboBox1;
    private JButton filtrarButton;
    private JButton ordenarButton;
    private JTable table3;
    private JTextArea textArea1;
    private JButton contarButton;
    private ListaSimple listaSimple = new ListaSimple();
    private ListaSimple listaFiltrada = new ListaSimple();
    private ListaSimple listaTele = new ListaSimple();
    private ListaSimple listaEnergia = new ListaSimple();
    private ListaSimple listaMagia = new ListaSimple();
    private ListaSimple listaSupFuer = new ListaSimple();
    private ListaSimple listaArqueria = new ListaSimple();
    private YoungAvenger avenger = null;

    public GUIPrueba() {
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(listaSimple.buscarLineal(Integer.parseInt(codigo.getText())) != -1) {

                    JOptionPane.showMessageDialog(null, "Ya existe ese pana");
                }else{
                    //int codigo, String nombre, String poderEspecial, String nivelHabilidad, int misionActiva
                    avenger = new YoungAvenger(Integer.parseInt(codigo.getText()), nombre.getText(), poderEspecial.getSelectedItem().toString(), Integer.parseInt(nivelHabilidad.getSelectedItem().toString()), misionActiva.getSelectedItem().toString());
                    listaSimple.agregarAvenger(avenger);
                    JOptionPane.showMessageDialog(null, "Guerrero agregado con exito");
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                codigo.setText("");
                nombre.setText("");
            }
        });
        generarTablaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listaSimple.mostrar(table1, 0, true);
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listaSimple.mostrar(table2, listaSimple.buscarLineal(Integer.parseInt(codigoBuscar.getText())), false);
            }
        });
        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listaFiltrada = listaSimple.filtrar(comboBox1.getSelectedItem().toString(), false);
                listaFiltrada.mostrar(table3, 0, true);
            }
        });
        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listaFiltrada.ordenar();
                listaFiltrada.mostrar(table3, 0, true);
            }
        });
        contarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listaTele = listaSimple.filtrar("Teletransportacion", true);
                listaEnergia = listaSimple.filtrar("Manipulacion de energia", true);
                listaMagia = listaSimple.filtrar("Magia", true);
                listaSupFuer = listaSimple.filtrar("Super fuerza", true);
                listaArqueria = listaSimple.filtrar("Arqueria", true);

                int conteoTele = listaTele.contarElementos(listaTele, 0);
                int conteoEner = listaEnergia.contarElementos(listaEnergia, 0);
                int conteoMagia = listaMagia.contarElementos(listaMagia, 0);
                int conteoSupFuer = listaSupFuer.contarElementos(listaSupFuer, 0);
                int conteoArqueria =listaArqueria.contarElementos(listaArqueria, 0);

                textArea1.setText("Misiones de Teletransportacion: " + Integer.toString(conteoTele) + "\n" +
                        "Misiones de Manipulacion de energia: " + Integer.toString(conteoEner) + "\n" +
                        "Misiones de Magia: " + Integer.toString(conteoMagia) + "\n" +
                        "Misiones de Super Fuerza: " + Integer.toString(conteoSupFuer) + "\n" +
                        "Misiones de Arqueria: " + Integer.toString(conteoArqueria));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUIPrueba");
        frame.setContentPane(new GUIPrueba().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
