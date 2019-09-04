package com.example.demo.repositories;

import com.example.demo.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Productos, Integer> {
    List<Productos> findByReferencia(String referencia);
}