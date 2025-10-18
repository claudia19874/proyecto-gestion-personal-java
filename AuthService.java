/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ep.interfaz;

/**
 *
 * @author chrojas
 */
public class AuthService {
  
    public boolean verificarAcceso(String usuario, String contrasena) {
        
        // --- LÓGICA DE ACCESO (Hardcodeada para el ejemplo) ---
        final String USUARIO_VALIDO = "adminEP";
        final String CONTRASENA_VALIDA = "12345"; 

        if (usuario.equals(USUARIO_VALIDO) && contrasena.equals(CONTRASENA_VALIDA)) {
            return true; // Acceso concedido
        } else {
            return false; // Credenciales incorrectas
        }
    }  
}
