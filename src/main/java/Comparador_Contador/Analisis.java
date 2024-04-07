package Comparador_Contador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Analisis extends JFrame {
    private JButton botonAnalizar;
    private JTextArea areaResultado;

    public Analisis() {
        super("Análisis de Texto");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarUI();
    }

    private void inicializarUI() {
        botonAnalizar = new JButton("Analizar Documento");
        botonAnalizar.addActionListener(this::accionAnalizar);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(botonAnalizar, BorderLayout.NORTH);
        panel.add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        panel.setBackground(new Color(246, 193, 128));

        this.add(panel);
    }

    private void accionAnalizar(ActionEvent e) {
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setCurrentDirectory(new File("./documentos"));
        selectorArchivo.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de texto (*.txt)", "txt")); // Filtra solo archivos .txt

        int resultado = selectorArchivo.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = selectorArchivo.getSelectedFile();
            try {
                String contenido = new String(Files.readAllBytes(Paths.get(archivoSeleccionado.getAbsolutePath())));
                StringTokenizer tokens = new StringTokenizer(contenido);
                areaResultado.setText("Número de palabras: " + tokens.countTokens());
            } catch (IOException excepcionIO) {
                JOptionPane.showMessageDialog(this, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

