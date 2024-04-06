import Validacion.Dibujo;
import Validacion.Validador;

import javax.swing.*;
import java.awt.*;

public class Aplicacion extends JFrame {

    public Aplicacion() {
        super("Aplicación Integrada - Empresa X");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.addTab("Editor y Documentos", crearPanelEditor()); // Aquí asumo que crearás este panel según las instrucciones previas
        pestañas.addTab("Validador de Email", new Validador().getPanel());
        pestañas.addTab("Herramienta de Dibujo", new Dibujo().getPanelDibujo());

        // Configuración de colores y estilo
        pestañas.setBackground(new Color(105, 105, 105));
        pestañas.setForeground(Color.BLACK);

        getContentPane().add(pestañas);
        setLocationRelativeTo(null);
    }

    // Asume que este método devuelve un JPanel configurado correctamente
    private JPanel crearPanelEditor() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Funcionalidades del Editor"));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Aplicacion app = new Aplicacion();
            app.setVisible(true);
        });
    }
}
