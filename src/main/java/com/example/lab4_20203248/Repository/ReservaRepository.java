package com.example.lab4_20203248.Repository;

import com.example.lab4_20203248.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
