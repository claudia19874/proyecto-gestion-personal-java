/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ep.interfaz;

/**
 *
 * @author chrojas
 */
public class Profesor {
    
    // Atributos privados
    private String id;
    private String nombreCompleto;
    private boolean activo; 

    // Constructor
    public Profesor(String id, String nombreCompleto, boolean activo) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.activo = activo;
    }

    // Métodos Getters (para acceder a los datos de forma controlada)
    public String getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public boolean isActivo() {
        return activo;
    }

    // Método auxiliar para la tabla
    public String getEstadoTexto() {
        return this.activo ? "Activo" : "Inactivo";
    }
}
