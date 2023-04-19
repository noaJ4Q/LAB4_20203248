package com.example.lab4_20203248.Repository;

import com.example.lab4_20203248.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(nativeQuery = true, value = "select iduser from user where email = ?1 and password = ?2")
    Integer obtenerCredenciales(String email, String password);
}
