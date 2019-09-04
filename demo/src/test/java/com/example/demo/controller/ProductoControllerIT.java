package com.example.demo.controller;
import com.example.demo.entity.Productos;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
public class ProductoControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductoService productoService;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
    }

    @Test
    public void addProduct() throws Exception {
        Productos user = new Productos(1, "hulk","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        mockMvc.perform(MockMvcRequestBuilders.post("/productos/addproducto", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    public void updateProduct() throws Exception {
        Productos user = new Productos(1, "hulk","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        mockMvc.perform(MockMvcRequestBuilders.post("/productos/addproducto", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Productos user2 = new Productos(1, "iron man","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        mockMvc.perform(MockMvcRequestBuilders.put("/productos/updateproducto", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user2)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Optional<Productos> productosEntity = productoService.getProductoById(1);
        Productos producto= productosEntity.get();
        assertThat(producto.getName()).isEqualTo("iron man");

    }

    @Test
    public void deleteProduct() throws Exception {
        Productos user = new Productos(1, "hulk","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        mockMvc.perform(MockMvcRequestBuilders.post("/productos/addproducto", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();

        Optional<Productos> productosEntity = productoService.getProductoById(1);
        Productos producto= productosEntity.get();
        assertThat(producto.getName()).isEqualTo("hulk");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/productos/delete/1");
        mockMvc.perform(builder)
                .andExpect(ok);

    }

    @Test
    public void deleteAllProduct() throws Exception {
        Productos user = new Productos(1, "hulk","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        mockMvc.perform(MockMvcRequestBuilders.post("/productos/addproducto", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/productos/deleteall");
        mockMvc.perform(builder)
                .andExpect(ok);

    }

    @Test
    public void getProductbyID() throws Exception {
        Productos user = new Productos(1, "hulk","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        mockMvc.perform(MockMvcRequestBuilders.post("/productos/addproducto", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/productos/1", 42L)
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void getAllProduct() throws Exception {
        Productos user = new Productos(1, "hulk","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        mockMvc.perform(MockMvcRequestBuilders.post("/productos/addproducto", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Productos user2 = new Productos(1, "iron man","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        mockMvc.perform(MockMvcRequestBuilders.put("/productos/updateproducto", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user2)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/productos/all", 42L)
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
