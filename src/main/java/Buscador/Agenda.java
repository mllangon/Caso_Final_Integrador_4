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

public class Agenda extends JFrame {
    private JTextField campoNombre, campoEmail, campoTelefono;
    private JButton botonGuardar, botonMostrar;
    private JTextArea areaContactos;
    private final File archivoContactos = new File("./documentos/contactos.txt");

    public Agenda() {
        super("Agenda de Contactos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarUI();
    }

    private void inicializarUI() {
        campoNombre = new JTextField();
        campoEmail = new JTextField();
        campoTelefono = new JTextField();
        botonGuardar = new JButton("Guardar Contacto");
        botonMostrar = new JButton("Mostrar Contactos");
        areaContactos = new JTextArea();
        areaContactos.setEditable(false);

        botonGuardar.addActionListener(this::accionGuardarContacto);
        botonMostrar.addActionListener(this::accionMostrarContactos);

        JPanel panelCampos = new JPanel(new GridLayout(0, 2));
        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(campoNombre);
        panelCampos.add(new JLabel("Email:"));
        panelCampos.add(campoEmail);
        panelCampos.add(new JLabel("Teléfono:"));
        panelCampos.add(campoTelefono);

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonGuardar);
        panelBotones.add(botonMostrar);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelCampos, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(new JScrollPane(areaContactos), BorderLayout.SOUTH);

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


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Agenda frame = new Agenda();
            frame.setVisible(true);
        });
    }
}
