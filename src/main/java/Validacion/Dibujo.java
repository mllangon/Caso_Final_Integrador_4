package Validacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Dibujo {
    private JPanel panelDibujo;

    public Dibujo() {
        inicializarUI();
    }

    private void inicializarUI() {
        panelDibujo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Configuración inicial del área de dibujo, si es necesario.
            }
        };
        panelDibujo.setPreferredSize(new Dimension(400, 400));
        panelDibujo.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                dibujarPunto(e.getX(), e.getY(), panelDibujo.getGraphics());
            }
        });
    }

    private void dibujarPunto(int x, int y, Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 4, 4);
    }

    public JPanel getPanelDibujo() {
        return panelDibujo;
    }
}

