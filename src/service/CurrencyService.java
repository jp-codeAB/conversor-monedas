package service;

import client.ExchangeRateClient;
import model.ExchangeRateResponse;

public class CurrencyService {
    private final ExchangeRateClient client = new ExchangeRateClient();

    public void executeConversion(String base, String target, double amount) {
        try {
            if (amount <= 0){
                throw new IllegalArgumentException("El monto debe ser mayor que cero");
            }
            ExchangeRateResponse response = client.searchConversion(base, target, amount);
            System.out.printf(">>> El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]%n",
                    amount, base, response.conversion_result(), target);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}