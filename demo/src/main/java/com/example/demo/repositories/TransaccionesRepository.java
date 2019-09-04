package com.example.demo.repositories;

import com.example.demo.entity.Transacciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionesRepository extends JpaRepository<Transacciones, Integer> {

}