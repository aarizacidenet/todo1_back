package com.example.demo.service;

import com.example.demo.repositories.ProductoRepository;
import com.example.demo.entity.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    public List<Productos> getAllProductos() {
        return this.productoRepository.findAll();
    }
    public List<Productos> getProductosForReferencia(String referencia) {
        return this.productoRepository.findByReferencia(referencia);
    }

    public Productos addAndUpdateProducto(Productos producto) {
            return this.productoRepository.saveAndFlush(producto);

    }

    public Optional<Productos> getProductoById(int id) {
        return this.productoRepository.findById(id);
    }
    public void deleteProductoById(int id) {
        this.productoRepository.deleteById(id);
    }

    public void deleteAllProductos() {
        this.productoRepository.deleteAll();
    }

}
