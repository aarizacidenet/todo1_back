package com.example.demo.controller;

import com.example.demo.entity.Productos;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Productos> getAllProductos() {
        return productoService.getAllProductos();
    }

    @RequestMapping(value = "/addproducto", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public Productos addNewProducto(@RequestBody Productos newproducto) {
        Productos producto = this.productoService.addAndUpdateProducto(newproducto);
        return producto;

    }

    @RequestMapping(value = "/updateproducto", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Productos updateProducto(@RequestBody Productos oficina) {
        return this.productoService.addAndUpdateProducto(oficina);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Productos> getProduct(@PathVariable int id) {
        return this.productoService.getProductoById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
    public void deleteAllProductos() {
        this.productoService.deleteAllProductos();
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteProducto(@PathVariable int id) {
        this.productoService.deleteProductoById(id);
    }




}