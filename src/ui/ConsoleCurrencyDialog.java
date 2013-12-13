package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import model.Currency;
import model.CurrencySet;

public class ConsoleCurrencyDialog{
    
    private Currency currency;
    
    public Currency execute() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (currency == null){
            System.out.println("Introduzca el código de una divisa");
            currency = CurrencySet.getInstance().get(reader.readLine());
        }    
        return currency;
    }
    
    public Currency getCurrency(){
        return currency;
    }
}
