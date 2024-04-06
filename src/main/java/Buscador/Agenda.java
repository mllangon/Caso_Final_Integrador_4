package Buscador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Agenda extends JFrame {
    private JTextField campoNombre, campoEmail, campoTelefono;
    private JButton botonGuardar, botonMostrar;
    private JButton botonEliminar;
    private JTextArea areaContactos;
    private final File archivoContactos = new File("./documentos/contactos.txt");

    public Agenda() {
        super("Agenda de Contactos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarUI();
    }

    private void inicializarUI() {
        campoNombre = new JTextField(20);
        campoEmail = new JTextField(20);
        campoTelefono = new JTextField(20);
        botonGuardar = new JButton("Guardar Contacto");
        botonMostrar = new JButton("Mostrar Contactos");
        botonEliminar = new JButton("Eliminar Contacto"); // Inicializar el botón de eliminar
        areaContactos = new JTextArea(10, 30);
        areaContactos.setEditable(false);

        botonGuardar.addActionListener(this::accionGuardarContacto);
        botonMostrar.addActionListener(this::accionMostrarContactos);
        botonEliminar.addActionListener(this::accionEliminarContacto); // Configurar listener para el botón de eliminar

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(3, 2));
        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(campoNombre);
        panelCampos.add(new JLabel("Email:"));
        panelCampos.add(campoEmail);
        panelCampos.add(new JLabel("Teléfono:"));
        panelCampos.add(campoTelefono);

        JPanel panelBotones = new JPanel(); // Asegúrate de que esta línea esté antes de añadir botones a él.
        panelBotones.add(botonGuardar);
        panelBotones.add(botonMostrar);
        panelBotones.add(botonEliminar); // Añadir el botón de eliminar al panel de botones

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelCampos, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(new JScrollPane(areaContactos), BorderLayout.SOUTH);

        panelPrincipal.setBackground(new Color(246, 193, 128));

        this.add(panelPrincipal);
    }


    private void accionGuardarContacto(ActionEvent e) {
        String contacto = String.format("%s, %s, %s%n", campoNombre.getText(), campoEmail.getText(), campoTelefono.getText());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoContactos, true))) {
            writer.write(contacto);
            JOptionPane.showMessageDialog(this, "Contacto guardado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            campoNombre.setText("");
            campoEmail.setText("");
            campoTelefono.setText("");
        } catch (IOException excepcionIO) {
            JOptionPane.showMessageDialog(this, "Error al guardar el contacto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionMostrarContactos(ActionEvent e) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(archivoContactos.getAbsolutePath()));
            // Asegurarse de que el área de texto esté limpia antes de agregar el contenido.
            areaContactos.setText(""); // Limpia el área de texto antes de agregar los contactos.
            for(String linea : lineas) {
                areaContactos.append(linea + "\n"); // Añade cada contacto al área de texto.
            }
        } catch (IOException excepcionIO) {
            JOptionPane.showMessageDialog(this, "Error al leer los contactos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionEliminarContacto(ActionEvent e) {
        String nombreEliminar = JOptionPane.showInputDialog(this, "Ingrese el nombre del contacto a eliminar:");
        if (nombreEliminar != null && !nombreEliminar.trim().isEmpty()) {
            try {
                List<String> lineas = Files.readAllLines(Paths.get(archivoContactos.getAbsolutePath()));
                List<String> lineasModificadas = lineas.stream()
                        .filter(linea -> !linea.split(",")[0].trim().equalsIgnoreCase(nombreEliminar.trim()))
                        .collect(Collectors.toList());

                if (lineas.size() == lineasModificadas.size()) {
                    JOptionPane.showMessageDialog(this, "No se encontró el contacto: " + nombreEliminar, "Información", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoContactos, false))) {
                    for (String linea : lineasModificadas) {
                        writer.write(linea);
                        writer.newLine();
                    }
                }

                JOptionPane.showMessageDialog(this, "Contacto eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                accionMostrarContactos(null); // Actualizar la lista de contactos mostrada.
            } catch (IOException excepcionIO) {
                JOptionPane.showMessageDialog(this, "Error al modificar los contactos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
