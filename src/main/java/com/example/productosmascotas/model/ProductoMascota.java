package com.example.productosmascotas.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data

public class ProductoMascota {

    private Long id;
    private String nombreProducto;
    private String destino;
    private String ubicacionActual;
    private EstadoEnvio estadoEnvio;

    public ProductoMascota(Long id, String nombreProducto, String destino, String ubicacionActual, EstadoEnvio estadoEnvio) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.destino = destino;
        this.ubicacionActual = ubicacionActual;
        this.estadoEnvio = estadoEnvio;
    }

    // Getters y Setters

    public Long getId(){
        return id;
    }

    public String getNombreProducto(){
        return nombreProducto;
    }

    public String getDestino(){
        return destino;
    }

    public String getUbicacionActual(){
        return ubicacionActual;
    }

    public EstadoEnvio getEstadoEnvio(){
        return estadoEnvio;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setNombreProducto(String nombreProducto){
        this.nombreProducto = nombreProducto;
    }

    public void setDestino(String destino){
        this.destino = destino;
    }

    public void setUbicacionActual(String ubicacionActual){
        this.ubicacionActual = ubicacionActual;
    }

    public void setEstadoEnvio(EstadoEnvio estadoEnvio){
        this.estadoEnvio = estadoEnvio;
    }

    

}
