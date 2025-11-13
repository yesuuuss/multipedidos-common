package com.multipedidos.common;

import java.util.Random;

/**
 * Librería de operaciones de negocio compartidas entre microservicios 
 */
public class OperacionesNegocio {

    private static final double IVA_PORCENTAJE = 0.12; 

    public static double calcularTotalConIVA(double subtotal) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("El subtotal no puede ser negativo");
        }
        return subtotal * (1 + IVA_PORCENTAJE);
    }

    public static double aplicarDescuento(double total, double porcentajeDescuento) {
        if (total < 0) {
            throw new IllegalArgumentException("El total no puede ser negativo");
        }
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100");
        }
        return total - (total * (porcentajeDescuento / 100));
    }

    public static boolean validarCodigo(String codigo) {
        if (codigo == null) return false;
        return codigo.matches("[A-Z]{3}-\\d{4}");
    }

    public static String generarNumeroFactura(String prefijo) {
        if (prefijo == null || prefijo.trim().isEmpty()) {
            prefijo = "FAC";
        }
        
        Random random = new Random();
        int numero = random.nextInt(9000) + 1000; 
        return prefijo + "-" + numero;
    }

    public static double calcularTotalConDescuento(double subtotal, double porcentajeDescuento) {
        double totalConIVA = calcularTotalConIVA(subtotal);
        return aplicarDescuento(totalConIVA, porcentajeDescuento);
    }
}