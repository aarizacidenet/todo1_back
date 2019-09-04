package com.example.demo.entity;


import javax.persistence.*;

@Entity
@Table(name = "TRANSACCIONES_TABLE")
public class Transacciones {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "TIPO", nullable = true, length = 255)
    private String tipo;

    @Column(name = "CANTIDAD", nullable = true, length = 255)
    private String cantidad;

    @Column(name = "VALOR_TOTAL", nullable = true, length = 100)
    private String total;

    @Column(name = "PRODUCTO_ID", nullable = true, length = 100)
    private String productoId;

    protected Transacciones() {
    }
    //constructor, setters and getters omitted for brevity


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public Transacciones(Integer id, String tipo, String cantidad, String total, String productoId) {
        this.id = id;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.total = total;
        this.productoId = productoId;
    }
}
