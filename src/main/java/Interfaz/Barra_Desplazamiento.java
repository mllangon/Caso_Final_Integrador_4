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

        add(scrollPane);
    }
}
