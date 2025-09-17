package com.example.business_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO {
    private Long id;
    private Long productoId;
    private Integer cantidad;
    private Integer stockMinimo;
    private String fechaActualizacion;
}
