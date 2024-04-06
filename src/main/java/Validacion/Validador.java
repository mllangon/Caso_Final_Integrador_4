package Validacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

public class Validador {
    private JPanel panelPrincipal;
    private JTextField campoEmail;
    private JLabel etiquetaEstado;

    public Validador() {
        inicializarUI();
    }

    private void inicializarUI() {
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(246, 193, 128));
        campoEmail = new JTextField(20);
        campoEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validarEmail(campoEmail.getText());
            }
        });

        etiquetaEstado = new JLabel("Ingrese un email para validar");
        panelPrincipal.add(campoEmail, BorderLayout.NORTH);
        panelPrincipal.add(etiquetaEstado, BorderLayout.CENTER);
    }

    private void validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (Pattern.matches(regex, email)) {
            etiquetaEstado.setText("Email válido");
        } else {
            etiquetaEstado.setText("Email inválido");
        }
    }

    public JPanel getPanel() {
        return panelPrincipal;
    }
}
