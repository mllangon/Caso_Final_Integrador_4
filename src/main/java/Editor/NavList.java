package Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class NavList extends JFrame {
    private JList<File> listaArchivos;
    private DefaultListModel<File> modeloLista;
    private final String directorioDocumentos = "./documentos";

    public NavList() {
        super("Navegar y Listar Documentos");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarUI();
    }

    private void inicializarUI() {
        modeloLista = new DefaultListModel<>();
        listaArchivos = new JList<>(modeloLista);
        listaArchivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cargarDocumentos();

        JButton botonRefrescar = new JButton("Refrescar");
        botonRefrescar.addActionListener(this::accionRefrescar);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(listaArchivos), BorderLayout.CENTER);
        panel.add(botonRefrescar, BorderLayout.SOUTH);

        panel.setBackground(new Color(246, 193, 128));

        this.add(panel);
    }

    public void cargarDocumentos() {
        modeloLista.clear();
        File carpeta = new File(directorioDocumentos);
        File[] listaDeArchivos = carpeta.listFiles((dir, nombre) -> nombre.endsWith(".txt"));
        if (listaDeArchivos != null) {
            for (File archivo : listaDeArchivos) {
                modeloLista.addElement(archivo);
            }
        }
    }

    private void accionRefrescar(ActionEvent e) {
        cargarDocumentos();
    }
}