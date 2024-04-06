package Comparador_Contador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Comparacion extends JFrame {
    private JButton botonComparar;
    private JTextArea areaResultado;

    public Comparacion() {
        super("Comparar Archivos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarUI();
    }

    private void inicializarUI() {
        botonComparar = new JButton("Comparar Archivos");
        botonComparar.addActionListener(this::accionComparar);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(botonComparar, BorderLayout.NORTH);
        panel.add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        panel.setBackground(new Color(246, 193, 128));

        this.add(panel);
    }

    private void accionComparar(ActionEvent e) {
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setCurrentDirectory(new File("./documentos")); // Configura el directorio inicial
        selectorArchivo.setMultiSelectionEnabled(true);
        selectorArchivo.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de texto (*.txt)", "txt")); // Filtra solo archivos .txt

        int resultado = selectorArchivo.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File[] archivosSeleccionados = selectorArchivo.getSelectedFiles();
            if (archivosSeleccionados.length == 2) {
                try {
                    String contenidoArchivo1 = new String(Files.readAllBytes(Paths.get(archivosSeleccionados[0].getAbsolutePath())));
                    String contenidoArchivo2 = new String(Files.readAllBytes(Paths.get(archivosSeleccionados[1].getAbsolutePath())));

                    if (contenidoArchivo1.equals(contenidoArchivo2)) {
                        areaResultado.setText("Los archivos son idénticos.");
                    } else {
                        areaResultado.setText("Los archivos son diferentes.");
                    }
                } catch (IOException excepcionIO) {
                    JOptionPane.showMessageDialog(this, "Error al leer los archivos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona dos archivos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
