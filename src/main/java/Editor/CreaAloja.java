package Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreaAloja extends JFrame {
    private JTextArea areaTexto;
    private JButton botonGuardar;
    private final String directorioDocumentos = "./documentos"; // Directorio para guardar documentos

    public CreaAloja() {
        super("Crear y Almacenar Textos");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarUI();
        verificarYCrearDirectorio();
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

    private void verificarYCrearDirectorio() {
        File directorio = new File(directorioDocumentos);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }

    private void accionGuardarTexto(ActionEvent e) {
        String nombreArchivo = JOptionPane.showInputDialog(this, "Nombre del archivo:", "Guardar como", JOptionPane.PLAIN_MESSAGE);
        if (nombreArchivo != null && !nombreArchivo.trim().isEmpty()) {
            File archivo = new File(directorioDocumentos, nombreArchivo.trim() + ".txt");
            try (FileWriter escritor = new FileWriter(archivo, false)) {
                escritor.write(areaTexto.getText());
                JOptionPane.showMessageDialog(this, "Texto guardado con éxito en " + archivo.getAbsolutePath(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException excepcionIO) {
                JOptionPane.showMessageDialog(this, "Error al guardar el texto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreaAloja frame = new CreaAloja();
            frame.setVisible(true);
        });
    }
}
