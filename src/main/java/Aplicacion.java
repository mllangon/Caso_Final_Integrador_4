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
    private Validacion.Dibujo dibujo = new Validacion.Dibujo();
    private Validacion.Validador validador = new Validacion.Validador();
    private Image backgroundImage;

    public Aplicacion() {
        super("StartUp - Plataforma de Gestión de Contenidos Digitales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        backgroundImage = new ImageIcon("src/resources/background.jpg").getImage();

        initComponents();
    }

    private void initComponents() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(210, 180, 140));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel logoLabel = new JLabel(new ImageIcon("src/resources/startup_logo.png"));
        headerPanel.add(logoLabel);

        cardPanel.add(creaAloja.getContentPane(), "Crear y Guardar");
        cardPanel.add(navList.getContentPane(), "Lista de Archivos");
        cardPanel.add(palabras.getContentPane(), "Buscador de Palabras");
        cardPanel.add(agenda.getContentPane(), "Agenda");
        cardPanel.add(multiplicidad.getContentPane(), "Archivos");
        cardPanel.add(dibujo.getPanelDibujo(), "Dibujo");
        cardPanel.add(validador.getPanel(), "Comprobar existencia de contctos");
        cardPanel.addMouseMotionListener(this);

        // Panel de navegación
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        navigationPanel.setBackground(new Color(154, 112, 55)); // Fondo marrón claro para el panel de navegación
        String[] buttons = {"Crear y Guardar", "Lista de Archivos", "Buscador de Palabras", "Agenda", "Archivos", "Dibujo", "Comprobar existencia de contctos"};
        for (String buttonLabel : buttons) {
            JButton button = new JButton(buttonLabel);
            button.addActionListener(this);
            navigationPanel.add(button);
        }

        add(headerPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(new Color(236, 162, 73));
        southPanel.add(statusBar, BorderLayout.NORTH);
        southPanel.add(navigationPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);

        cardPanel.setBackground(new Color(246, 193, 128));
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

            Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueue() {
                @Override
                protected void dispatchEvent(AWTEvent event) {
                    super.dispatchEvent(event);
                    if (event instanceof MouseEvent) {
                        MouseEvent me = (MouseEvent) event;
                        if (me.getID() == MouseEvent.MOUSE_MOVED) {
                            app.actualizarTitulo(me);
                        }
                    }
                }
            });
        });
    }

}
