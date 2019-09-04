package com.example.demo.controller;

import com.example.demo.entity.Transacciones;
import com.example.demo.entity.ErrorMEnsaje;
import com.example.demo.service.TransaccionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/transacciones")
public class TransaccionController {
    @Autowired
    TransaccionesService transaccionesService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Transacciones> getAllTransacciones() {
        return transaccionesService.getAllTransacciones();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public Transacciones addNewTransaccion(@RequestBody Transacciones transaccion) {
        Transacciones newtransaccion = this.transaccionesService.addAndUpdateTransaccion(transaccion);
        return newtransaccion;

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Transacciones updateTransaccion(@RequestBody Transacciones contacto) {
        return this.transaccionesService.addAndUpdateTransaccion(contacto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Transacciones> getTransaccion(@PathVariable int id) {
        return this.transaccionesService.getTransaccionById(id);
    }

    @RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
    public void deleteAllTransacciones() {
        this.transaccionesService.deleteAllTransaccion();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteTransaccion(@PathVariable int id) {
        this.transaccionesService.deleteTransaccionById(id);
    }


}