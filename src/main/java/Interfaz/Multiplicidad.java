package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Multiplicidad extends JFrame {
    private JTabbedPane pestañas;
    private final File directorioDocumentos = new File("./documentos");

    public Multiplicidad() {
        super("Multiplicidad de Documentos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarUI();
        cargarDocumentos();
    }

    private void inicializarUI() {
        pestañas = new JTabbedPane();
        pestañas.setBackground(new Color(246, 193, 128));
        add(pestañas);
    }

    public void cargarDocumentos() {
        pestañas.removeAll(); // Limpia las pestañas antes de cargar los documentos
        if (directorioDocumentos.exists() && directorioDocumentos.isDirectory()) {
            File[] archivos = directorioDocumentos.listFiles((dir, nombre) -> nombre.endsWith(".txt"));
            if (archivos != null) {
                for (File archivo : archivos) {
                    try {
                        String contenido = new String(Files.readAllBytes(Paths.get(archivo.getAbsolutePath())));
                        JTextArea areaTexto = new JTextArea(contenido);
                        JScrollPane scrollPane = new JScrollPane(areaTexto);
                        pestañas.addTab(archivo.getName(), scrollPane);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(this, "Error al cargar el documento: " + archivo.getName(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
}