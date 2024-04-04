package Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

public class CreaAloja extends JFrame {
    private JTextArea areaTexto;
    private JButton botonGuardar;

    public CreaAloja() {
        super("Crear y Almacenar Textos");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarUI();
    }

    private void inicializarUI() {
        areaTexto = new JTextArea();
        botonGuardar = new JButton("Guardar");

        botonGuardar.addActionListener(this::accionGuardarTexto);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        panel.add(botonGuardar, BorderLayout.SOUTH);

        this.add(panel);
    }

    private void accionGuardarTexto(ActionEvent e) {
        try (FileWriter escritor = new FileWriter("texto_guardado.txt")) {
            escritor.write(areaTexto.getText());
            JOptionPane.showMessageDialog(this, "Texto guardado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException excepcionIO) {
            JOptionPane.showMessageDialog(this, "Error al guardar el texto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreaAloja frame = new CreaAloja();
            frame.setVisible(true);
        });
    }
}
