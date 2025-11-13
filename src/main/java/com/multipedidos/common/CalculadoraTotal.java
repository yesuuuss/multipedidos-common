package com.multipedidos.common;

import java.util.List;

public class CalculadoraTotal {
    
    public static double calcularTotal(List<Producto> productos) {
        if (productos == null || productos.isEmpty()) {
            return 0.0;
        }
        
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }
}