package com.example.demo.services;
import com.example.demo.entity.Productos;
import com.example.demo.entity.Transacciones;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.service.ProductoService;
import com.example.demo.service.TransaccionesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.engine.transaction.internal.TransactionImpl;
import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.xml.bind.ValidationException;

import static org.assertj.core.api.Assertions.*;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransaccionesServiceIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransaccionesService transaccionesService = new TransaccionesService();

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
    }

    @Test
    public void addTRansaccion() throws Exception {
        Transacciones transaccion = new Transacciones(1,"compra","15","447","vaso hulk");

        Transacciones response = transaccionesService.addAndUpdateTransaccion(transaccion);
        transaccion = response;
        Optional<Transacciones> result = transaccionesService.getTransaccionById(transaccion.getId());
        Transacciones userresponse = result.get();
        Assert.assertEquals(userresponse.getTipo(),transaccion.getTipo());
        Assert.assertEquals(userresponse.getId(),transaccion.getId());
        Assert.assertEquals(userresponse.getCantidad(),transaccion.getCantidad());
        Assert.assertEquals(userresponse.getTotal(),transaccion.getTotal());
        Assert.assertEquals(userresponse.getProductoId(),transaccion.getProductoId());
    }


    @Test
    public void updateTransaccion() throws Exception {
        Transacciones transaccion = new Transacciones(1,"compra","15","447","vaso hulk");

        Transacciones response1 =  transaccionesService.addAndUpdateTransaccion(transaccion);
        transaccion = response1;
        Transacciones transaccionEdit = new Transacciones(transaccion.getId(),"venta","15","447","vaso hulk");

        Transacciones response = transaccionesService.addAndUpdateTransaccion(transaccionEdit);
        transaccionEdit = response;

        Optional<Transacciones> result = transaccionesService.getTransaccionById(transaccionEdit.getId());
        Transacciones userresponse = result.get();
        Assert.assertEquals(userresponse.getTipo(),transaccionEdit.getTipo());
        Assert.assertEquals(userresponse.getId(),transaccion.getId());
        Assert.assertEquals(userresponse.getCantidad(),transaccion.getCantidad());
        Assert.assertEquals(userresponse.getTotal(),transaccion.getTotal());
        Assert.assertEquals(userresponse.getProductoId(),transaccion.getProductoId());

    }

    @Test
    public void deleteTransaccion() throws Exception {
        Transacciones transaccion = new Transacciones(1,"compra","15","447","vaso hulk");

        transaccionesService.addAndUpdateTransaccion(transaccion);

        transaccionesService.deleteTransaccionById(1);

        Optional<Transacciones> result2= transaccionesService.getTransaccionById(1);
        Boolean userresponse2 = result2.isPresent();
        Assert.assertFalse(userresponse2);

    }

    @Test
    public void deleteAllTransacciones() throws Exception {
        Transacciones transaccion = new Transacciones(1,"compra","15","447","vaso hulk");

        transaccionesService.addAndUpdateTransaccion(transaccion);

        Transacciones transaccionEdit = new Transacciones(2,"venta","15","447","vaso hulk");

        transaccionesService.addAndUpdateTransaccion(transaccionEdit);

        transaccionesService.deleteAllTransaccion();

        List<Transacciones> trasnsacciones = transaccionesService.getAllTransacciones();

        Assert.assertTrue(trasnsacciones.size() == 0);


    }

    @Test
    public void getTransaccionbyID() throws Exception {
        Transacciones transaccion = new Transacciones(1,"compra","15","447","vaso hulk");

        Transacciones response = transaccionesService.addAndUpdateTransaccion(transaccion);
        transaccion = response;
        Optional<Transacciones> result = transaccionesService.getTransaccionById(response.getId());
        Transacciones userresponse = result.get();
        Assert.assertEquals(userresponse.getTipo(),transaccion.getTipo());
        Assert.assertEquals(userresponse.getId(),transaccion.getId());
        Assert.assertEquals(userresponse.getCantidad(),transaccion.getCantidad());
        Assert.assertEquals(userresponse.getTotal(),transaccion.getTotal());
        Assert.assertEquals(userresponse.getProductoId(),transaccion.getProductoId());

    }
    @Test
    public void getAllTransacciones() throws Exception {
        Transacciones transaccion = new Transacciones(1,"compra","15","447","vaso hulk");

        Transacciones response = transaccionesService.addAndUpdateTransaccion(transaccion);
        transaccion = response;
        Optional<Transacciones> result = transaccionesService.getTransaccionById(response.getId());
        Transacciones userresponse = result.get();
        Assert.assertEquals(userresponse.getTipo(),transaccion.getTipo());
        Assert.assertEquals(userresponse.getId(),transaccion.getId());
        Assert.assertEquals(userresponse.getCantidad(),transaccion.getCantidad());
        Assert.assertEquals(userresponse.getTotal(),transaccion.getTotal());
        Assert.assertEquals(userresponse.getProductoId(),transaccion.getProductoId());

        List<Transacciones> trasnsacciones = transaccionesService.getAllTransacciones();

        Assert.assertNotNull(trasnsacciones);
        Assert.assertTrue(trasnsacciones.size() > 0);

        Assert.assertEquals(trasnsacciones.get(0).getProductoId(), "vaso hulk");
    }

}
