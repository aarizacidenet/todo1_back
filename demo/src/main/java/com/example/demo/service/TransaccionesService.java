package com.example.demo.service;

import com.example.demo.repositories.TransaccionesRepository;
import com.example.demo.entity.Transacciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransaccionesService {
    @Autowired
    TransaccionesRepository transaccionesRepository;

    public List<Transacciones> getAllTransacciones() {
        return this.transaccionesRepository.findAll();
    }


    public Transacciones addAndUpdateTransaccion(Transacciones transacion) {
        return this.transaccionesRepository.saveAndFlush(transacion);

    }

    public Optional<Transacciones> getTransaccionById(int id) {

        return this.transaccionesRepository.findById(id);
    }

    public void deleteTransaccionById(int id) {

        this.transaccionesRepository.deleteById(id);
    }

    public void deleteAllTransaccion() {

        this.transaccionesRepository.deleteAll();
    }

}
