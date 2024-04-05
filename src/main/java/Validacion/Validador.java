package Validacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

public class Validador extends JFrame {
    private JTextField campoEmail;
    private JLabel etiquetaEstado;

    public Validador() {
        super("Validador de Email");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarUI();
    }

    private void inicializarUI() {
        campoEmail = new JTextField(20);
        campoEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validarEmail(campoEmail.getText());
            }
        });

        etiquetaEstado = new JLabel("Ingrese un email para validar");
        JPanel panel = new JPanel();
        panel.add(campoEmail);
        panel.add(etiquetaEstado);

        add(panel);
    }

    private void validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (Pattern.matches(regex, email)) {
            etiquetaEstado.setText("Email válido");
        } else {
            etiquetaEstado.setText("Email inválido");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Validador frame = new Validador();
            frame.setVisible(true);
        });
    }
}
