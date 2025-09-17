package com.example.business_service.service;

import com.example.business_service.client.DataServiceClient;
import com.example.business_service.dto.CategoriaDTO;
import com.example.business_service.exception.CategoriaNoEncontradaException;
import com.example.business_service.exception.MicroserviceCommunicationException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class CategoriaBusinessService {

    private final DataServiceClient dataServiceClient;

    public CategoriaBusinessService(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }

    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        try {
            log.info("Obteniendo todas las categorías del microservicio de datos");
            return dataServiceClient.obtenerTodasLasCategorias();
        } catch (FeignException e) {
            log.error("Error al obtener categorías del microservicio de datos: {}", e.getMessage());
            throw new MicroserviceCommunicationException("Error de comunicación con el servicio de datos al obtener categorías.");
        }
    }

    public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO) {
        try {
            log.info("Creando nueva categoría en el microservicio de datos");
            return dataServiceClient.crearCategoria(categoriaDTO);
        } catch (FeignException e) {
            log.error("Error al crear categoría en el microservicio de datos: {}", e.getMessage());
            throw new MicroserviceCommunicationException("Error de comunicación con el servicio de datos al crear categoría.");
        }
    }
}
