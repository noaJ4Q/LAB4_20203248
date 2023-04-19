package com.example.lab4_20203248.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reserva")
@Getter
@Setter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idreserva;
    @Column(nullable = false)
    private String fecha_reserva;
    @Column(nullable = false)
    private float precio_total;
    @Column(nullable = false, length = 45)
    private String estado_pago;
    @ManyToOne
    @JoinColumn(name = "user_iduser")
    private User user;
    @ManyToOne
    @JoinColumn(name = "vuelo_idvuelo")
    private Vuelo vuelo;
}
