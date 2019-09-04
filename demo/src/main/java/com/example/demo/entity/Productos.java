package com.example.demo.entity;


import javax.persistence.*;

@Entity
@Table(name = "PRODUCTO_TABLE")
public class Productos {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "PRODUCTO_NAME", nullable = true, length = 100)
    private String name;

    @Column(name = "PRODUCTO_DESCRIPCION", nullable = true, length = 255)
    private String descripcion;

    @Column(name = "PRODUCTO_DETALLE", nullable = true, length = 100)
    private String detalle;

    @Column(name = "PRODUCTO_REFERENCIA", nullable = true, length = 10)
    private String referencia;

    @Column(name = "PRODUCTO_VALOR_UNITARIO", nullable = true, length = 10)
    private String valor;

    @Column(name = "PRODUCTO_CANTIDAD", nullable = true, length = 10)
    private String cantidad;

    @Column(name = "PRODUCTO_CODIGO", nullable = true, length = 100)
    private String codigo;

    @Column(name = "PRODUCTO_TIPO", nullable = true, length = 10)
    private String tipo;

    protected Productos(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Productos(Integer id, String name, String descripcion, String detalle, String referencia, String valor, String cantidad, String codigo, String tipo) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.referencia = referencia;
        this.valor = valor;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.tipo = tipo;
    }
}
