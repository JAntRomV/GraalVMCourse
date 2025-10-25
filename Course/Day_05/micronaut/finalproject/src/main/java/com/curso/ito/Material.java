package com.curso.ito;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.core.annotation.ReflectiveAccess;
import io.micronaut.core.annotation.Introspected;

@ReflectiveAccess
@Introspected
@Serdeable
public class Material {

    private String tipo;
    private double toneladas;
    private double precio;
    private int descuento;
    private double precioSinDes;
    private double precioTotal;

    // Constructor vacío
    public Material() {}

    // Constructor con parámetros
    public Material(String tipo, double toneladas) {
        this.tipo = tipo;
        this.toneladas = toneladas;
    }

    public Material(String tipo, double toneladas, double precio) {
        this.tipo = tipo;
        this.toneladas = toneladas;
        this.precio = precio;
    }

    public Material(String tipo, double toneladas, double precio, int descuento) {
        this.tipo = tipo;
        this.toneladas = toneladas;
        this.precio = precio;
        this.descuento = descuento;
    }

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getToneladas() {
        return toneladas;
    }

    public void setToneladas(double toneladas) {
        this.toneladas = toneladas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public double getPrecioSinDes(){
        return precioSinDes;
    }

    public void setPrecioSinDes(double precioSinDes){
        this.precioSinDes = precioSinDes;
    }

    public double getPrecioTotal(){
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal){
        this.precioTotal = precioTotal;
    }
}