package com.multipedidos.common;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.time.Duration;

public class HttpClientUtil {
    
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    
    // Método que llama al Componente A para validar un cliente
    public static boolean validarClienteEnSistemaA(Long clienteId) {
        try {
            String url = "http://localhost:8080/clientes/" + clienteId;
            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            
            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());
            
            return response.statusCode() == 200;
            
        } catch (Exception e) {
            System.err.println("Error validando cliente: " + e.getMessage());
            return false;
        }
    }
}