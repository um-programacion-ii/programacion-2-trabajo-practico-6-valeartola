package com.example.business_service.service;

import com.example.business_service.client.DataServiceClient;
import com.example.business_service.dto.InventarioDTO;
import com.example.business_service.exception.InventarioNoEncontradoException;
import com.example.business_service.exception.MicroserviceCommunicationException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InventarioBusinessService {
    private final DataServiceClient dataServiceClient;

    public InventarioBusinessService(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }

    public List<InventarioDTO> obtenerProductosConStockBajo() {
        try {
            log.info("Obteniendo productos con stock bajo del microservicio de datos");
            return dataServiceClient.obtenerProductosConStockBajo();
        } catch (FeignException e) {
            log.error("Error al obtener productos con stock bajo del microservicio de datos: {}", e.getMessage());
            throw new MicroserviceCommunicationException("Error de comunicación con el servicio de datos al obtener inventario.");
        }
    }

    public InventarioDTO obtenerInventarioPorProductoId(Long productoId) {
        try {
            log.info("Consultando inventario para el producto ID: {}", productoId);
            return dataServiceClient.obtenerInventarioPorProductoId(productoId);
        } catch (FeignException.NotFound e) {
            log.error("Inventario no encontrado para el producto ID: {}", productoId);
            throw new InventarioNoEncontradoException("No se encontró inventario para el producto con ID " + productoId);
        } catch (FeignException e) {
            log.error("Error de comunicación al obtener el inventario: {}", e.getMessage());
            throw new MicroserviceCommunicationException("Error de comunicación al obtener el inventario.");
        }
    }

}
