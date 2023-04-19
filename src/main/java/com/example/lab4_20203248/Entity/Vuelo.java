package com.example.lab4_20203248.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vuelo")
@Getter
@Setter
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idvuelo;
    @Column(nullable = false, length = 45)
    private String origen;
    @Column(nullable = false, length = 45)
    private String destino;
    private String fecha_salida;
    private String fecha_llegada;
    @Column(nullable = false)
    private int duracion;
    @Column(nullable = false)
    private float precio;
    @ManyToOne
    @JoinColumn(name = "aerolinea_idaerolinea")
    private Aerolinea aerolinea;
    private int asientos_disponibles;
    @Column(nullable = false, length = 500)
    private String descripcion;
}
