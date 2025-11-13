package com.multipedidos.common;

import java.util.Random;
import java.util.List;

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

    
    /**
     * Procesa un pedido validando el cliente en Componente A integración circular
     */
    public static boolean procesarPedidoConValidacion(Long clienteId, List<Producto> productos) {
       
        boolean clienteValido = HttpClientUtil.validarClienteEnSistemaA(clienteId);
        
        if (!clienteValido) {
            System.out.println(" Cliente con ID " + clienteId + " no existe en el sistema");
            return false;
        }
        
   
        double subtotal = CalculadoraTotal.calcularTotal(productos);
        double totalConIVA = calcularTotalConIVA(subtotal);
        
     
        String codigoPedido = GeneradorCodigos.generarCodigoUnico("PED");
        
        System.out.println(" Pedido procesado - Código: " + codigoPedido + 
                         ", Cliente ID: " + clienteId + 
                         ", Total: $" + totalConIVA);
        return true;
    }
    
    /**
     * Método específico para calcular total de lista de productos nuevo requerimiento
     */
    public static double calcularTotal(List<Producto> productos) {
        return CalculadoraTotal.calcularTotal(productos);
    }
    
    /**
     * Método específico para generar código único nuevo requerimiento
     */
    public static String generarCodigoUnico(String tipoEntidad) {
        return GeneradorCodigos.generarCodigoUnico(tipoEntidad);
    }
}