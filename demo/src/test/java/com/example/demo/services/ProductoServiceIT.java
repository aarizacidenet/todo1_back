package com.example.demo.services;
import com.example.demo.entity.Productos;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.service.spi.ServiceException;
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

import javax.xml.bind.ValidationException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductoServiceIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductoService productoService = new ProductoService();

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
    }

    @Test()
    public void addProduct() throws ServiceException, ValidationException {
        Productos user = new Productos(1, "hulk","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");
        Productos response = productoService.addAndUpdateProducto(user);
        Optional<Productos> result = productoService.getProductoById(response.getId());
        user = response;
        Productos userresponse = result.get();
        Assert.assertEquals(userresponse.getName(),user.getName());
        Assert.assertEquals(userresponse.getId(),user.getId());
        Assert.assertEquals(userresponse.getDescripcion(),user.getDescripcion());
        Assert.assertEquals(userresponse.getDetalle(),user.getDetalle());
        Assert.assertEquals(userresponse.getReferencia(),user.getReferencia());
        Assert.assertEquals(userresponse.getValor(),user.getValor());
        Assert.assertEquals(userresponse.getCantidad(),user.getCantidad());
        Assert.assertEquals(userresponse.getCodigo(),user.getCodigo());
    }

    @Test
    public void updateProduct() throws Exception {
        Productos user = new Productos(1, "hulk","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        productoService.addAndUpdateProducto(user);

        Productos user2 = new Productos(1, "iron man","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");


        productoService.addAndUpdateProducto(user2);

        Optional<Productos> result = productoService.getProductoById(1);
        Productos userresponse = result.get();
        Assert.assertEquals(userresponse.getName(),user2.getName());
        Assert.assertEquals(userresponse.getId(),user.getId());
        Assert.assertEquals(userresponse.getDescripcion(),user.getDescripcion());
        Assert.assertEquals(userresponse.getDetalle(),user.getDetalle());
        Assert.assertEquals(userresponse.getReferencia(),user.getReferencia());
        Assert.assertEquals(userresponse.getValor(),user.getValor());
        Assert.assertEquals(userresponse.getCantidad(),user.getCantidad());
        Assert.assertEquals(userresponse.getCodigo(),user.getCodigo());

    }

    @Test
    public void deleteProduct() throws Exception {
        Productos user = new Productos(3, "hulk","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        Productos response = productoService.addAndUpdateProducto(user);

        productoService.deleteProductoById(response.getId());

        Optional<Productos> result2= productoService.getProductoById(response.getId());
        Boolean userresponse2 = result2.isPresent();
        Assert.assertFalse(userresponse2);

    }

    @Test
    public void deleteAllProduct() throws Exception {
        Productos user = new Productos(12, "hulk","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        productoService.addAndUpdateProducto(user);


        Productos user2 = new Productos(7, "iron man","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        productoService.addAndUpdateProducto(user2);

        productoService.deleteAllProductos();

        List<Productos> users = productoService.getAllProductos();
        Assert.assertTrue(users.size() == 0);


    }

    @Test
    public void getProductbyID() throws Exception {
        Productos user = new Productos(1, "hulk","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        productoService.addAndUpdateProducto(user);
        Optional<Productos> result = productoService.getProductoById(1);
        Productos userresponse = result.get();
        Assert.assertEquals(userresponse.getName(),user.getName());
        Assert.assertEquals(userresponse.getId(),user.getId());
        Assert.assertEquals(userresponse.getDescripcion(),user.getDescripcion());
        Assert.assertEquals(userresponse.getDetalle(),user.getDetalle());
        Assert.assertEquals(userresponse.getReferencia(),user.getReferencia());
        Assert.assertEquals(userresponse.getValor(),user.getValor());
        Assert.assertEquals(userresponse.getCantidad(),user.getCantidad());
        Assert.assertEquals(userresponse.getCodigo(),user.getCodigo());

    }
    @Test
    public void getAllProduct() throws Exception {
        Productos user = new Productos(1, "hulk","vaso bonito","es muy lindo",
                "1","12548","1254","1254","1");

        productoService.addAndUpdateProducto(user);

        List<Productos> users = productoService.getAllProductos();

        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() > 0);

        Assert.assertEquals(users.get(0).getName(), "hulk");
    }

}
