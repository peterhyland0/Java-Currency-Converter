package assign6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class currency_converter {
    static class CurrencyConverter {

    public static void main(String[] args) {
        // Read exchange rates from rate.txt file
        HashMap<String, Double> exchangeRates = readExchangeRates("currency.txt");

        // Prompt user to input the from and to currencies and the amount to exchange
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the from currency: ");
        String fromCurrency = input.nextLine();
        System.out.print("Enter the to currency: ");
        String toCurrency = input.nextLine();
        System.out.print("Enter the amount to exchange: ");
        double amount = input.nextDouble();

        // Convert the amount from the from currency to the to currency
        double fromRate = exchangeRates.get(fromCurrency);
        double toRate = exchangeRates.get(toCurrency);
        double convertedAmount = amount * toRate / fromRate;

        // Print the result
        System.out.printf("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency);
    }

    // Reads exchange rates from the specified file and returns them as a map
    private static HashMap<String, Double> readExchangeRates(String filename) {
        HashMap<String, Double> exchangeRates = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] wordsAndNumber = line.split("\\s+");
                String currencyName = "";
                double rate = 0.0;
                for (String wordOrNumber : wordsAndNumber) {
                    if (wordOrNumber.matches("[a-zA-Z]+")) {
                        currencyName += wordOrNumber + " ";
                    } else if (wordOrNumber.matches("\\d+(\\.\\d+)?")) {
                        rate = Double.parseDouble(wordOrNumber);
                    }
                }
                exchangeRates.put(currencyName.trim(), rate);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Invalid exchange rate in file: " + filename);
            System.exit(1);
        }
        return exchangeRates;
    }
}}

