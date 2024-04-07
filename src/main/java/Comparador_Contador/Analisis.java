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
        botonAnalizar.addActionListener(this::accionBotonAnalizar);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(botonAnalizar, BorderLayout.NORTH);
        panel.add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        panel.setBackground(new Color(246, 193, 128));

        this.add(panel);
    }

    private void accionBotonAnalizar(ActionEvent e) {
        JFileChooser selectorArchivo = new JFileChooser();
        int resultado = selectorArchivo.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = selectorArchivo.getSelectedFile();
            accionAnalizar(archivoSeleccionado);
        }
    }

    public int accionAnalizar(File archivoSeleccionado) {
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(archivoSeleccionado.getAbsolutePath())));
            StringTokenizer tokens = new StringTokenizer(contenido);
            int totalPalabras = tokens.countTokens();
            areaResultado.setText("Número de palabras: " + totalPalabras);
            return totalPalabras;
        } catch (IOException excepcionIO) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
}