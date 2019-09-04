package com.example.demo.controller;
import com.example.demo.entity.Productos;
import com.example.demo.entity.Transacciones;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.service.ProductoService;
import com.example.demo.service.TransaccionesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.engine.transaction.internal.TransactionImpl;
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
import static org.assertj.core.api.Assertions.*;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransaccionesControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransaccionesService transaccionesService;

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

        mockMvc.perform(MockMvcRequestBuilders.post("/transacciones/add", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(transaccion)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void updateTransaccion() throws Exception {
        Transacciones transaccion = new Transacciones(1,"compra","15","447","vaso hulk");

        mockMvc.perform(MockMvcRequestBuilders.post("/transacciones/add", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(transaccion)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Transacciones transaccion3 = new Transacciones(1,"venta","15","447","vaso hulk");

        mockMvc.perform(MockMvcRequestBuilders.put("/transacciones/update", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(transaccion3)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deleteTransaccion() throws Exception {
        Transacciones transaccion = new Transacciones(1,"compra","15","447","vaso hulk");


        mockMvc.perform(MockMvcRequestBuilders.post("/transacciones/add", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(transaccion)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();

        Optional<Transacciones> transaccionEntity = transaccionesService.getTransaccionById(1);
        Transacciones producto= transaccionEntity.get();
        assertThat(producto.getTipo()).isEqualTo("compra");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/transacciones/delete/1");
        mockMvc.perform(builder)
                .andExpect(ok);

    }

    @Test
    public void deleteAllTransaccion() throws Exception {
        Transacciones transaccion = new Transacciones(1,"compra","15","447","vaso hulk");


        mockMvc.perform(MockMvcRequestBuilders.post("/transacciones/add", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(transaccion)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/transacciones/deleteall");
        mockMvc.perform(builder)
                .andExpect(ok);

    }

    @Test
    public void getTransaccionbyID() throws Exception {
        Transacciones transaccion = new Transacciones(1,"compra","15","447","vaso hulk");


        mockMvc.perform(MockMvcRequestBuilders.post("/transacciones/add", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(transaccion)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();

        mockMvc.perform(MockMvcRequestBuilders.get("/transacciones/1", 42L)
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void getAllTransaccion() throws Exception {
        Transacciones transaccion = new Transacciones(1,"compra","15","447","vaso hulk");


        mockMvc.perform(MockMvcRequestBuilders.post("/transacciones/add", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(transaccion)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();


        mockMvc.perform(MockMvcRequestBuilders.get("/transacciones/all", 42L)
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
