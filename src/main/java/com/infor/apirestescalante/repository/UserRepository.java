package com.infor.apirestescalante.repository;

import com.infor.apirestescalante.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.*;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByCiudad(String ciudad);
    List<User> findByFechaCreacionIsAfter(LocalDate fecha);
}
