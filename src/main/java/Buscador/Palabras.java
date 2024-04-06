package Buscador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Palabras extends JFrame {
    private JButton botonBuscar;
    private JTextArea areaResultado;
    private final String directorioDocumentos = "./documentos";

    public Palabras() {
        super("Buscar Palabra en Documento");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarUI();
    }

    private void inicializarUI() {
        botonBuscar = new JButton("Buscar Palabra");
        botonBuscar.addActionListener(this::accionBuscarPalabra);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(botonBuscar, BorderLayout.NORTH);
        panel.add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        this.add(panel);
    }

    private void accionBuscarPalabra(ActionEvent e) {
        JFileChooser selectorArchivo = new JFileChooser(directorioDocumentos);
        selectorArchivo.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));
        int resultado = selectorArchivo.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = selectorArchivo.getSelectedFile();
            String palabraABuscar = JOptionPane.showInputDialog(this, "Ingrese la palabra a buscar:");
            if (palabraABuscar != null && !palabraABuscar.trim().isEmpty()) {
                try {
                    String contenido = new String(Files.readAllBytes(Paths.get(archivoSeleccionado.getAbsolutePath())));
                    Pattern pattern = Pattern.compile("\\b" + Pattern.quote(palabraABuscar.trim()) + "\\b", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(contenido);
                    int contador = 0;
                    while (matcher.find()) {
                        contador++;
                    }
                    areaResultado.setText("La palabra '" + palabraABuscar + "' aparece " + contador + " veces en el documento.");
                } catch (IOException excepcionIO) {
                    JOptionPane.showMessageDialog(this, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
