package com.tuempresa.serviciosweb.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuempresa.serviciosweb.model.Cliente;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    public List<Cliente> ObtenerClientes() throws IOException, InterruptedException {
        String url = System.getenv("SUPABASE_ENDPOINT");
        String apiKey = System.getenv("SUPABASE_API_KEY");

        if (url == null || url.isBlank()) {
            throw new IOException("Falta SUPABASE_ENDPOINT en esta ejecución.");
        }

        if (apiKey == null || apiKey.isBlank()) {
            throw new IOException("Falta SUPABASE_API_KEY en esta ejecución.");
        }

        URI endpoint;
        try {
            endpoint = URI.create(url.trim());
        } catch (IllegalArgumentException error) {
            throw new IOException("SUPABASE_ENDPOINT tiene un formato inválido.");
        }

        if (!"https".equalsIgnoreCase(endpoint.getScheme()) || endpoint.getHost() == null) {
            throw new IOException("SUPABASE_ENDPOINT debe ser una URL HTTPS válida.");
        }

        HttpClient cliente = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpoint)
                .timeout(Duration.ofSeconds(15))
                .header("apikey", apiKey.trim())
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = cliente.send(
                request, HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException("Error al consultar clientes. HTTP " + response.statusCode());
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

        JsonNode datos = mapper.readTree(response.body());

        if (datos == null || !datos.isArray()) {
            throw new IOException("Supabase no devolvió un arreglo JSON de clientes.");
        }

        List<Cliente> clientes = new ArrayList<>();

        for (JsonNode nodo : datos) {
            JsonNode ingreso = nodo.path("annual_income");
            JsonNode puntaje = nodo.path("credit_score");
            JsonNode deuda = nodo.path("has_unpaid_debt");
            JsonNode montoDeuda = nodo.path("unpaid_debt_amount");

            Cliente clienteSupabase = new Cliente(
                    nodo.path("id").asLong(),
                    nodo.path("first_name").asText(null),
                    nodo.path("last_name").asText(null),
                    nodo.path("email").asText(null),
                    nodo.path("phone").asText(null),
                    nodo.path("date_of_birth").asText(null),
                    nodo.path("country").asText(null),
                    nodo.path("city").asText(null),
                    nodo.path("address").asText(null),
                    ingreso.isNumber() ? ingreso.decimalValue() : null,
                    puntaje.isIntegralNumber() ? puntaje.intValue() : null,
                    deuda.isBoolean() ? deuda.booleanValue() : null,
                    montoDeuda.isNumber() ? montoDeuda.decimalValue() : null,
                    nodo.path("status").asText(null),
                    nodo.path("created_at").asText(null),
                    nodo.path("updated_at").asText(null)
            );

            clientes.add(clienteSupabase);
        }

        return clientes;
    }
}