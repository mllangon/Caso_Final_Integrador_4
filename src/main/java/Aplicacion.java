import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aplicacion extends JFrame implements ActionListener {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private JLabel statusBar = new JLabel(" Listo");

    public Aplicacion() {
        super("StartUp - Plataforma de Gestión de Contenidos Digitales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        initComponents();
    }

    private void initComponents() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(128, 64, 0)); // Fondo marrón para el encabezado
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("StartUp");
        titleLabel.setForeground(Color.BLACK); // Letras negras
        headerPanel.add(titleLabel);

        // Logo en el panel de bienvenida
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(Color.GRAY);
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/startup_logo.png"));
        JLabel logoLabel = new JLabel(logoIcon);
        welcomePanel.add(logoLabel, BorderLayout.CENTER);
        cardPanel.add(welcomePanel, "Bienvenida");

        // Añadir paneles de funcionalidad
        // Ejemplo: cardPanel.add(new FuncionalidadPanel(), "Funcionalidad");

        // Panel de navegación
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] buttons = {"Bienvenida", "Funcionalidad1", "Funcionalidad2"};
        for (String buttonLabel : buttons) {
            JButton button = new JButton(buttonLabel);
            button.addActionListener(this);
            navigationPanel.add(button);
        }

        // Configuración final de la ventana
        add(headerPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        add(navigationPanel, BorderLayout.SOUTH);

        // Seguimiento del ratón global (opcional)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cardLayout.show(cardPanel, e.getActionCommand());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Aplicacion app = new Aplicacion();
            app.setVisible(true);
        });
    }
}
