package com.multipedidos.common;

import java.util.UUID;

public class GeneradorCodigos {
    
    public static String generarCodigoUnico(String tipoEntidad) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String uuid = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
        return tipoEntidad + "-" + timestamp + "-" + uuid;
    }
}