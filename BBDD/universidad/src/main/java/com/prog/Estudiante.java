package com.prog;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Estudiante {
    private int id;
    private String matricula, nombre, apellidos, email, tlf;
    private LocalDate fecha_nac;
    private boolean activo;
    private LocalDateTime created_at, update_at;
    
    public Estudiante(int id, String matricula, String nombre, String apellidos, String email, String tlf,
            LocalDate fecha_nac, int activo) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.tlf = tlf;
        this.fecha_nac = fecha_nac;
        this.activo = true;
    }

    public Estudiante(int id, String matricula, String nombre, String apellidos, String email, String tlf,
            LocalDate fecha_nac, boolean activo, LocalDateTime created_at, LocalDateTime update_at) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.tlf = tlf;
        this.fecha_nac = fecha_nac;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
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
