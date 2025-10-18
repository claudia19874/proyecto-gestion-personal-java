/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ep.interfaz;

/**
 *
 * @author chrojas
 */
public class main {
    public static void main(String[] args) {
        
        // El código de inicialización de la interfaz
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Crear una instancia de tu ventana de login y hacerla visible
                new LoginFrame().setVisible(true);
            }
        });
    }
}
