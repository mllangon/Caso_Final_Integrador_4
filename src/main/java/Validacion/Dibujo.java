package Validacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Dibujo extends JFrame {
    private JPanel panelDibujo;

    public Dibujo() {
        super("Herramienta de Dibujo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarUI();
    }

    private void inicializarUI() {
        panelDibujo = new JPanel();
        panelDibujo.setBackground(Color.WHITE);
        panelDibujo.setPreferredSize(new Dimension(400, 400));

        panelDibujo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dibujarPunto(e.getX(), e.getY());
            }
        });

        panelDibujo.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                dibujarPunto(e.getX(), e.getY());
            }
        });

        add(panelDibujo);
    }

    private void dibujarPunto(int x, int y) {
        Graphics g = panelDibujo.getGraphics();
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 4, 4);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Dibujo frame = new Dibujo();
            frame.setVisible(true);
        });
    }
}
