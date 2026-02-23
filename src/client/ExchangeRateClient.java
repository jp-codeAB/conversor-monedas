package client;


import com.google.gson.Gson;
import exception.CurrencyApiException;
import model.ExchangeRateResponse;
import util.JsonParserUtil;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateClient {
    String key="a506ab6dff99dee66462a37a";
    public ExchangeRateResponse searchConversion(String coinOne, String coinTwo, double valueConversion) {
        URI addresCoin = URI.create("https://v6.exchangerate-api.com/v6/"+key+"/pair/"+coinOne+"/"+coinTwo+"/"+valueConversion);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(addresCoin)
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            ExchangeRateResponse exchangeResponse =
                    JsonParserUtil.fromJson(response.body(), ExchangeRateResponse.class);

            if (!"success".equalsIgnoreCase(exchangeResponse.result())) {
                throw new CurrencyApiException("Error en la respuesta de la API");
            }
            return exchangeResponse;
        } catch (IOException | InterruptedException e) {
            throw new CurrencyApiException("Error al conectar con la API", e);
        }
    }


}