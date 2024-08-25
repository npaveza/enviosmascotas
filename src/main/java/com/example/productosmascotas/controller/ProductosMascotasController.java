package com.example.productosmascotas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productosmascotas.model.EstadoEnvio;
import com.example.productosmascotas.model.ProductoMascota;

@RestController
@RequestMapping("/api/envios")
public class ProductosMascotasController {

    private final List<ProductoMascota> envios = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    public ProductosMascotasController() {
        // Inicialización con datos de pruebas
        envios.add(new ProductoMascota(idCounter.incrementAndGet(), "Alimento para Perro", "Ciudad A", "Centro de Distribución", EstadoEnvio.PENDIENTE));
        envios.add(new ProductoMascota(idCounter.incrementAndGet(), "Arena para Gatos", "Ciudad B", "En camino a Ciudad B", EstadoEnvio.EN_CAMINO));
        envios.add(new ProductoMascota(idCounter.incrementAndGet(), "Juguete para Perro", "Ciudad C", "Centro de Distribución", EstadoEnvio.PENDIENTE));
        envios.add(new ProductoMascota(idCounter.incrementAndGet(), "Caseta para Perro", "Ciudad D", "En camino a Ciudad D", EstadoEnvio.EN_CAMINO));
        envios.add(new ProductoMascota(idCounter.incrementAndGet(), "Rascador para Gatos", "Ciudad E", "Centro de Distribución", EstadoEnvio.PENDIENTE));
        envios.add(new ProductoMascota(idCounter.incrementAndGet(), "Comedero Automático", "Ciudad F", "En camino a Ciudad F", EstadoEnvio.EN_CAMINO));
        envios.add(new ProductoMascota(idCounter.incrementAndGet(), "Collar Antipulgas", "Ciudad G", "Entregado", EstadoEnvio.ENTREGADO));
        envios.add(new ProductoMascota(idCounter.incrementAndGet(), "Cama para Gato", "Ciudad H", "Entregado", EstadoEnvio.ENTREGADO));
    }

    @GetMapping
    public List<ProductoMascota> obtenerEnvios() {
        return envios;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoMascota> obtenerEnvioPorId(@PathVariable Long id) {
        return envios.stream()
                .filter(envio -> envio.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoMascota> registrarEnvio(@RequestBody ProductoMascota nuevoEnvio) {
        nuevoEnvio.setId(idCounter.incrementAndGet());
        envios.add(nuevoEnvio);
        return ResponseEntity.ok(nuevoEnvio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoMascota> actualizarEnvio(@PathVariable Long id, @RequestBody ProductoMascota envioActualizado) {
        return envios.stream()
                .filter(envio -> envio.getId().equals(id))
                .findFirst()
                .map(envio -> {
                    envio.setNombreProducto(envioActualizado.getNombreProducto());
                    envio.setDestino(envioActualizado.getDestino());
                    envio.setUbicacionActual(envioActualizado.getUbicacionActual());
                    envio.setEstadoEnvio(envioActualizado.getEstadoEnvio());
                    return ResponseEntity.ok(envio);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable Long id) {
        boolean removed = envios.removeIf(envio -> envio.getId().equals(id));
        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/ubicacion")
    public ResponseEntity<String> consultarUbicacion(@PathVariable Long id) {
        return envios.stream()
                .filter(envio -> envio.getId().equals(id))
                .map(ProductoMascota::getUbicacionActual)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/update/{id}/{nuevoEstado}")
    public ResponseEntity<ProductoMascota> actualizarEstadoEnvio(
            @PathVariable Long id, 
            @PathVariable EstadoEnvio nuevoEstado) {
        Optional<ProductoMascota> envioOpt = envios.stream()
                .filter(envio -> envio.getId().equals(id))
                .findFirst();
    
        if (envioOpt.isPresent()) {
            ProductoMascota envio = envioOpt.get();
            envio.setEstadoEnvio(nuevoEstado);
            return ResponseEntity.ok(envio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
