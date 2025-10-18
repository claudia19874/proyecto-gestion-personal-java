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
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon; 
import javax.swing.border.LineBorder;

public class LoginFrame extends JFrame {

    private JPanel panel;
    private JLabel labelLogo, labelTitulo, labelUsuario, labelContrasena;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIniciarSesion;

    // Constructor de la ventana
    public LoginFrame() {
        // --- Configuración básica de la ventana ---
        setTitle("Elite Performance - Acceso");
        setSize(500, 600); 
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        panel = new JPanel();
        panel.setLayout(null); 
        panel.setBackground(Color.BLACK); 
        this.getContentPane().add(panel);

        // =======================================================
        // 1. LOGO 'EP.' (Con ImageIcon)
        // =======================================================
        try {
            // RUTA DE LA IMAGEN: Busca 'images.png' dentro de la carpeta 'images' que está
            // en el mismo paquete que esta clase (com.ep.interfaz)
            ImageIcon icono = new ImageIcon(getClass().getResource("images/images.png")); 
            labelLogo = new JLabel(icono); 
            
            // Definimos el tamaño deseado para el logo
            int logoAncho = 150;
            int logoAlto = 150;
            
            // Escalar la imagen para ajustarla al tamaño
            java.awt.Image img = icono.getImage();
            java.awt.Image imgEscalada = img.getScaledInstance(logoAncho, logoAlto, java.awt.Image.SCALE_SMOOTH);
            labelLogo.setIcon(new ImageIcon(imgEscalada));

            // Centra el logo y lo posiciona.
            labelLogo.setBounds((500 - logoAncho) / 2, 50, logoAncho, logoAlto); 
            labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
            
            panel.add(labelLogo);

        } catch (Exception e) {
            System.err.println("¡ERROR! No se pudo cargar la imagen. Usando texto de respaldo.");
            System.err.println("Detalles del error: " + e.getMessage());
            
            // Texto de respaldo
            labelLogo = new JLabel("EP."); 
            labelLogo.setBounds(200, 50, 100, 50); 
            labelLogo.setForeground(Color.WHITE);
            labelLogo.setFont(new Font("Arial Black", Font.BOLD, 48));
            labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(labelLogo);
        }

        // =======================================================
        // 2. TÍTULO, CAMPOS y BOTÓN (Layout)
        // =======================================================
        labelTitulo = new JLabel("ACCESO DE USUARIO");
        labelTitulo.setBounds(50, 220, 400, 30); 
        labelTitulo.setForeground(Color.WHITE);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(labelTitulo);

        labelUsuario = new JLabel("ID de usuario");
        labelUsuario.setBounds(50, 290, 400, 20);
        labelUsuario.setForeground(Color.WHITE);
        labelUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(labelUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(50, 320, 400, 30);
        txtUsuario.setCaretColor(Color.WHITE);
        txtUsuario.setBackground(Color.BLACK);
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setBorder(new LineBorder(Color.WHITE, 1)); 
        panel.add(txtUsuario);

        labelContrasena = new JLabel("Contraseña");
        labelContrasena.setBounds(50, 380, 400, 20);
        labelContrasena.setForeground(Color.WHITE);
        labelContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(labelContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(50, 410, 400, 30);
        txtContrasena.setCaretColor(Color.WHITE);
        txtContrasena.setBackground(Color.BLACK);
        txtContrasena.setForeground(Color.WHITE);
        txtContrasena.setBorder(new LineBorder(Color.WHITE, 1)); 
        panel.add(txtContrasena);

        btnIniciarSesion = new JButton("INICIAR SESIÓN");
        btnIniciarSesion.setBounds(150, 500, 200, 40);
        btnIniciarSesion.setBackground(Color.BLACK);
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 18));
        btnIniciarSesion.setBorder(new LineBorder(Color.WHITE, 2)); 
      
        btnIniciarSesion.addActionListener((ActionEvent e) -> {
            // 1. OBTENER DATOS DE LA INTERFAZ
            String usuario = txtUsuario.getText();
            // JPasswordField.getPassword() retorna un array de char, que es más seguro.
            String contrasena = new String(txtContrasena.getPassword());
            
            // 2. CREAR INSTANCIA DEL SERVICIO DE AUTENTICACIÓN
            AuthService authService = new AuthService();
            
            // 3. VERIFICAR ACCESO
            if (authService.verificarAcceso(usuario, contrasena)) {
                
                // ACCESO CONCEDIDO: Abrir el nuevo panel
    this.dispose(); 
    
    // ¡CAMBIO AQUÍ!
    GestionP panelPrincipal = new GestionP(); 
    panelPrincipal.setVisible(true);
                
            } else {
                
                // ACCESO DENEGADO
                System.err.println("Error de acceso: Credenciales inválidas.");
                javax.swing.JOptionPane.showMessageDialog(null, "Usuario o Contraseña inválidos.", "Error de Autenticación", javax.swing.JOptionPane.ERROR_MESSAGE);
                
                // Limpiar campos para reintento
                txtContrasena.setText("");
            }
        });
        
        panel.add(btnIniciarSesion);
    }
} 
