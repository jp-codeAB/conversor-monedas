package client;


import com.google.gson.Gson;
import model.ExchangeRateResponse;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateClient {
    String key="a506ab6dff99dee66462a37a";
    public ExchangeRateResponse searchConversion(String coinOne, String coinTwo, int valueConversion) {
        URI addresCoin = URI.create("https://v6.exchangerate-api.com/v6/"+key+"/pair/"+coinOne+"/"+coinTwo+"/"+valueConversion);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(addresCoin)
                .build();

        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error de conversion entre"+coinOne+" y "+coinTwo);
        }
        return new Gson().fromJson(response.body(), ExchangeRateResponse.class);
    }


}