package Interfaz;

import javax.swing.*;
import java.awt.*;

public class Barra_Desplazamiento extends JFrame {
    public Barra_Desplazamiento() {
        super("Barra de Desplazamiento Interactiva");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarUI();
    }

    private void inicializarUI() {
        JTextArea areaTexto = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(areaTexto);

        // Añadir la barra de desplazamiento al panel y configurarla si es necesario
        // Por defecto, JScrollPane añade barras de desplazamiento cuando el contenido excede el tamaño visible

        add(scrollPane);
    }
}
