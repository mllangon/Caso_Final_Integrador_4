package Interfaz;

import javax.swing.*;
import java.awt.*;

public class Multiplicidad extends JFrame {
    private JTabbedPane pestañas;

    public Multiplicidad() {
        super("Multiplicidad de Documentos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarUI();
    }

    private void inicializarUI() {
        pestañas = new JTabbedPane();

        JTextArea areaTexto1 = new JTextArea();
        pestañas.addTab("Documento 1", new JScrollPane(areaTexto1));

        JTextArea areaTexto2 = new JTextArea();
        pestañas.addTab("Documento 2", new JScrollPane(areaTexto2));

        // Aquí puedes añadir más documentos o incluso permitir al usuario abrir nuevos documentos en nuevas pestañas.

        add(pestañas);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Multiplicidad frame = new Multiplicidad();
            frame.setVisible(true);
        });
    }
}

