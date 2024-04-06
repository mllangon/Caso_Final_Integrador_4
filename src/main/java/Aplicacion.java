import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Objects;

public class Aplicacion extends JFrame implements ActionListener, MouseMotionListener {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private JLabel statusBar = new JLabel(" Listo");

    // Crear instancias de las clases
    private Editor.CreaAloja creaAloja = new Editor.CreaAloja();
    private Editor.NavList navList = new Editor.NavList();
    private Buscador.Palabras palabras = new Buscador.Palabras();
    private Buscador.Agenda agenda = new Buscador.Agenda();
    private Interfaz.Multiplicidad multiplicidad = new Interfaz.Multiplicidad();
    private Interfaz.Seguimiento seguimiento = new Interfaz.Seguimiento();
    private Validacion.Dibujo dibujo = new Validacion.Dibujo();
    private Validacion.Validador validador = new Validacion.Validador();

    public Aplicacion() {
        super("StartUp - Plataforma de Gestión de Contenidos Digitales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        initComponents();
    }

    private void initComponents() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(128, 64, 0)); // Fondo marrón para el encabezado
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel logoLabel = new JLabel(new ImageIcon("resources/logo.png")); // Asegúrate de que la ruta al logo es correcta
        headerPanel.add(logoLabel);

        // Añadir paneles de funcionalidad
        cardPanel.add(creaAloja.getContentPane(), "CreaAloja");
        cardPanel.add(navList.getContentPane(), "NavList");
        cardPanel.add(palabras.getContentPane(), "Palabras");
        cardPanel.add(agenda.getContentPane(), "Agenda");
        cardPanel.add(multiplicidad.getContentPane(), "Multiplicidad");
        cardPanel.add(seguimiento.getContentPane(), "Seguimiento");
        cardPanel.add(dibujo.getPanelDibujo(), "Dibujo");
        cardPanel.add(validador.getPanel(), "Validador");

        // Panel de navegación
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] buttons = {"CreaAloja", "NavList", "Palabras", "Agenda", "Multiplicidad", "Seguimiento", "Dibujo", "Validador"};
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

        // Seguimiento del ratón global
        addMouseMotionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        cardLayout.show(cardPanel, command);
        if (Objects.equals(command, "NavList") || Objects.equals(command, "Multiplicidad")) {
            navList.cargarDocumentos();
            multiplicidad.cargarDocumentos();
        }
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
        statusBar.setText("Posición del Ratón: [" + e.getX() + ", " + e.getY() + "]");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Aplicacion app = new Aplicacion();
            app.setVisible(true);
        });
    }
}