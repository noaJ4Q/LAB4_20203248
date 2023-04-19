package com.example.lab4_20203248.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aerolinea")
@Getter
@Setter
public class Aerolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idaerolinea;
    @Column(nullable = false, length = 45)
    private String nombre;
    @Column(nullable = false, length = 45)
    private String codigo;
}
