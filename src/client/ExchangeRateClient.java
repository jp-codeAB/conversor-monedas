package client;

import exception.CurrencyApiException;
import model.ExchangeRateResponse;
import util.JsonParserUtil;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateClient {
    // Definición de mi key en un variable
    String key="a506ab6dff99dee66462a37a";

    public ExchangeRateResponse searchConversion(String coinOne, String coinTwo, double valueConversion) {
        // Manejo de la URI desde una variable definida aparte
        URI addresCoin = URI.create("https://v6.exchangerate-api.com/v6/"+key+"/pair/"+coinOne+"/"+coinTwo+"/"+valueConversion);

        // creacion del Client
        HttpClient client = HttpClient.newHttpClient();

        //creación del HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(addresCoin)
                .build();

        try {
            //creación del HttpResponse
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            ExchangeRateResponse exchangeResponse =
                    JsonParserUtil.fromJson(response.body(), ExchangeRateResponse.class);

            if (!"success".equalsIgnoreCase(exchangeResponse.result())) {
                //manejo de error de respuesta por parte de la API
                throw new CurrencyApiException("Error en la respuesta de la API");
            }
            return exchangeResponse;
        } catch (IOException | InterruptedException e) {
            //manejo de error en caso de fallo de conexión
            throw new CurrencyApiException("Error al conectar con la API", e);
        }
    }
}