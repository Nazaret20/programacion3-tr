package com.prog;

import java.time.LocalDateTime;

public class Curso {
    private int id;
    private String clave, descripcion, nombre, semestre;
    private int creditos;
    private boolean activo;
    private LocalDateTime created_at, update_at;
   
    public Curso(String clave, String nombre, String descripcion, int creditos, String semestre) {
        this.clave = clave;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.semestre = semestre;
        this.activo = true; 
    }

    public Curso(int id, String clave, String descripcion, String nombre, String semestre, int creditos, boolean activo,
            LocalDateTime created_at, LocalDateTime update_at) {
        this.id = id;
        this.clave = clave;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.semestre = semestre;
        this.creditos = creditos;
        this.activo = activo;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(LocalDateTime update_at) {
        this.update_at = update_at;
    }
    
    
    
}
