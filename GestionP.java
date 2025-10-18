/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ep.interfaz;

/**
 *
 * @author chrojas
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;

// La clase principal se llama ahora GestionP
public class GestionP extends JFrame {

    private JPanel panelMenu, panelContenido;
    private JLabel labelLogo;
    
    // Elementos de la vista de Gestión de Profesores
    private JTable tablaProfesores;
    private JTextField txtBusqueda;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<DefaultTableModel> sorter;
    private List<Profesor> listaProfesores;

    // Constructor de la ventana
    public GestionP() { // Renombrado de PanelFrame() a GestionP()
        setTitle("Elite Performance - Gestión de Profesores");
        setSize(1000, 650); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true); 
        
        cargarDatosIniciales();
        iniciarComponentes();
    }
    
    // Método POO para cargar datos iniciales (simulación de base de datos)
    private void cargarDatosIniciales() {
        listaProfesores = new ArrayList<>();
        listaProfesores.add(new Profesor("123456", "Pepito Mengano Perez", true));
        listaProfesores.add(new Profesor("123457", "Maria Lopez Garcia", true));
        listaProfesores.add(new Profesor("123458", "Juan Alberto Diaz", false));
        listaProfesores.add(new Profesor("123459", "Sofia Martinez Luna", true));
        listaProfesores.add(new Profesor("123460", "Carlos Ruiz Torres", true));
        listaProfesores.add(new Profesor("123461", "Antonio Banderas G.", false));
    }

    private void iniciarComponentes() {
        // --- Configuración del Content Pane del JFrame ---
        this.getContentPane().setLayout(null); 
        this.getContentPane().setBackground(Color.BLACK); 
        
        // =======================================================
        // 1. PANEL LATERAL (Menú - Color Negro Sólido)
        // =======================================================
        panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBackground(Color.BLACK);
        panelMenu.setBounds(0, 0, 250, 650); 
        this.getContentPane().add(panelMenu);
        
        // Logo en el menú (EP.)
        try {
            ImageIcon icono = new ImageIcon(getClass().getResource("images/images.png")); 
            labelLogo = new JLabel(icono);
            java.awt.Image img = icono.getImage();
            java.awt.Image imgEscalada = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            labelLogo.setIcon(new ImageIcon(imgEscalada));
            labelLogo.setBounds(50, 20, 150, 150);
            panelMenu.add(labelLogo);
        } catch (Exception e) {
            // Fallback si la imagen no carga
            labelLogo = new JLabel("EP.");
            labelLogo.setBounds(50, 20, 150, 50);
            labelLogo.setForeground(Color.WHITE);
            labelLogo.setFont(new Font("Arial Black", Font.BOLD, 36));
            panelMenu.add(labelLogo);
        }
        
        // Botones de Menú: Solo activamos el de profesores
        agregarBotonMenu(panelMenu, "Gestión de profesores", 250, true);
        agregarBotonMenu(panelMenu, "Historial", 300, false); 
        
        // Botón CERRAR SESIÓN
        JButton btnCerrar = new JButton("CERRAR SESIÓN");
        btnCerrar.setBounds(20, 580, 210, 30);
        btnCerrar.setBackground(Color.BLACK); 
        btnCerrar.setForeground(new Color(178, 34, 34)); 
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setBorder(null); 
        btnCerrar.addActionListener(e -> {
            this.dispose(); // Cierra esta ventana (GestionP)
            new LoginFrame().setVisible(true); // Vuelve a abrir el login
        });
        panelMenu.add(btnCerrar);

        // =======================================================
        // 2. PANEL DE CONTENIDO (Derecha - VISTA PRINCIPAL)
        // =======================================================
        panelContenido = crearVistaGestionProfesores();
        panelContenido.setBounds(250, 0, 750, 650); 
        this.getContentPane().add(panelContenido);
    }
    
    // =======================================================
    // MÉTODO AUXILIAR: CREAR VISTA DE GESTIÓN DE PROFESORES
    // =======================================================
    private JPanel crearVistaGestionProfesores() {
        JPanel panelGestion = new JPanel();
        panelGestion.setLayout(null);
        panelGestion.setBackground(new Color(40, 40, 40)); 

        // Título
        JLabel lblTituloGestion = new JLabel("GESTIÓN DE PROFESORES");
        lblTituloGestion.setBounds(30, 30, 450, 30);
        lblTituloGestion.setForeground(Color.WHITE);
        lblTituloGestion.setFont(new Font("Arial", Font.BOLD, 28));
        panelGestion.add(lblTituloGestion);

        // Botón +Agregar Profesor
        JButton btnAgregar = new JButton("+Agregar Profesor");
        btnAgregar.setBounds(550, 30, 150, 30);
        btnAgregar.setBackground(new Color(60, 60, 60));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 14));
        panelGestion.add(btnAgregar);

        // Campo de Búsqueda (Filtro)
        txtBusqueda = new JTextField();
        txtBusqueda.setBounds(30, 90, 670, 35);
        txtBusqueda.setText("Buscar por apellido ,ID.....");
        txtBusqueda.setForeground(Color.GRAY);
        txtBusqueda.setFont(new Font("Arial", Font.PLAIN, 14));
        txtBusqueda.setBorder(new LineBorder(new Color(80, 80, 80), 1));
        txtBusqueda.setBackground(Color.WHITE);

        // LÓGICA DE FILTRADO DINÁMICO
        txtBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String textoFiltro = txtBusqueda.getText();
                if (textoFiltro.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoFiltro)); 
                }
            }
        });
        
        // Lógica para borrar el texto de placeholder al hacer foco
        txtBusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtBusqueda.getText().equals("Buscar por apellido ,ID.....")) {
                    txtBusqueda.setText("");
                    txtBusqueda.setForeground(Color.BLACK);
                }
            }
        });
        panelGestion.add(txtBusqueda);

        // --- TABLA DE DATOS (JTable) ---
        String[] nombresColumnas = {"ID", "Nombre completo", "Activo"};
        modeloTabla = new DefaultTableModel(nombresColumnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };

        // Llenar el modelo con los datos del modelo POO
        for (Profesor p : listaProfesores) {
            Object[] fila = {p.getId(), p.getNombreCompleto(), p.getEstadoTexto()};
            modeloTabla.addRow(fila);
        }

        tablaProfesores = new JTable(modeloTabla);
        tablaProfesores.setBackground(new Color(40, 40, 40));
        tablaProfesores.setForeground(Color.WHITE);
        tablaProfesores.setGridColor(new Color(80, 80, 80));
        tablaProfesores.setRowHeight(40); 
        tablaProfesores.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Seleccionar la fila de ejemplo 
        if (tablaProfesores.getRowCount() > 0) {
            tablaProfesores.setRowSelectionInterval(0, 0); 
        }
        
        // Configurar el filtro (sorter) a la tabla
        sorter = new TableRowSorter<>(modeloTabla);
        tablaProfesores.setRowSorter(sorter);

        // Contenedor de Scroll para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaProfesores);
        scrollPane.setBounds(30, 140, 670, 450); 
        scrollPane.getViewport().setBackground(new Color(40, 40, 40)); 
        scrollPane.setBorder(null);

        panelGestion.add(scrollPane);
        
        return panelGestion;
    }

    // Método auxiliar para crear botones de menú
    private void agregarBotonMenu(JPanel panel, String texto, int y, boolean activo) {
        JLabel separador = new JLabel(); 
        separador.setBounds(0, y, 250, 50);
        
        JButton btn = new JButton(texto);
        btn.setBounds(20, 0, 230, 50); 
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFont(new Font("Arial", Font.PLAIN, 18));
        
        // Configuración de estilo
        if (activo) {
            separador.setBackground(new Color(60, 60, 60)); 
            separador.setOpaque(true);
            btn.setBackground(new Color(60, 60, 60));
            btn.setForeground(Color.WHITE);
        } else {
            separador.setBackground(Color.BLACK);
            separador.setOpaque(true);
            btn.setBackground(Color.BLACK);
            btn.setForeground(Color.LIGHT_GRAY);
        }
        
        btn.setBorder(null);
        separador.add(btn); 
        panel.add(separador); 
    }
    
    // =======================================================
    // MINI MAIN (Para pruebas independientes)
    // =======================================================
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Inicia la clase GestionP directamente para pruebas
                new GestionP().setVisible(true); 
            }
        });
    }
}
