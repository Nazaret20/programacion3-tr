package com.prog;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Matriculas {
    private int id, estudiante_id, curso_id;
    private LocalDate fecha_matricula;
    private double parcial1, parcial2, parcial3, final_exam, promedio;
    private String estatus;
    private LocalDateTime created_at, update_at;
   
    public Matriculas(int id, int estudiante_id, int curso_id, LocalDate fecha_matricula, double parcial1,
            double parcial2, double parcial3, double final_exam, double promedio, String estatus,
            LocalDateTime created_at, LocalDateTime update_at) {
        this.id = id;
        this.estudiante_id = estudiante_id;
        this.curso_id = curso_id;
        this.fecha_matricula = fecha_matricula;
        this.parcial1 = parcial1;
        this.parcial2 = parcial2;
        this.parcial3 = parcial3;
        this.final_exam = final_exam;
        this.promedio = promedio;
        this.estatus = estatus;
        this.created_at = created_at;
        this.update_at = update_at;
    }


}
