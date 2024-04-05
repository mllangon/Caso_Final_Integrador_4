package Interfaz;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Seguimiento extends JFrame implements MouseMotionListener {
    public Seguimiento() {
        super("Seguimiento del Ratón");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        actualizarTitulo(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        actualizarTitulo(e);
    }

    private void actualizarTitulo(MouseEvent e) {
        setTitle("Posición del Ratón: [" + e.getX() + ", " + e.getY() + "]");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Seguimiento frame = new Seguimiento();
            frame.setVisible(true);
        });
    }
}

