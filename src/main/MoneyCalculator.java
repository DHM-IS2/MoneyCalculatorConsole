package main;

import control.ExchangeMoneyControl;
import java.io.IOException;
import persistence.FileCurrencySetLoader;
import persistence.MockExchangeRateLoader;
import ui.ConsoleCurrencyDialog;
import ui.ConsoleMoneyDialog;
import ui.ConsoleMoneyViewer;

public class MoneyCalculator {
            
    public static void main(String[] args) throws IOException {
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.execute();
    }
    
    public void execute() throws IOException{
        FileCurrencySetLoader fileCurrencySetLoader = FileCurrencySetLoader.getInstance();
        fileCurrencySetLoader.setFilename("C:\\Users\\Darwin\\Desktop\\currencies.txt");
        fileCurrencySetLoader.load();
        ConsoleMoneyDialog moneyDialog = new ConsoleMoneyDialog();
        ConsoleCurrencyDialog currencyDialog = new ConsoleCurrencyDialog();
        ConsoleMoneyViewer moneyViewer = new ConsoleMoneyViewer();
        MockExchangeRateLoader exchangeRateLoader = MockExchangeRateLoader.getInstance();        
        ExchangeMoneyControl exchangeMoneyControl = new ExchangeMoneyControl(moneyDialog, currencyDialog, moneyViewer, exchangeRateLoader);
        exchangeMoneyControl.execute();
    }
}
