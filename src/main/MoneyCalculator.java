package main;

import control.ExchangeMoneyControl;
import java.io.IOException;
import java.text.ParseException;
import persistence.DBExchangeRateLoader;
import persistence.ExchangeRateLoader;
import persistence.FileCurrencySetLoader;
import ui.ConsoleCurrencyDialog;
import ui.ConsoleDateDialog;
import ui.ConsoleMoneyDialog;
import ui.ConsoleMoneyViewer;

public class MoneyCalculator {

    public static void main(String[] args) throws IOException, ParseException {
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.execute();
    }

    public void execute() throws IOException, ParseException {
        FileCurrencySetLoader fileCurrencySetLoader = FileCurrencySetLoader.getInstance();
        fileCurrencySetLoader.setFilename("C:\\Users\\usuario\\Desktop\\currencies.txt");
        fileCurrencySetLoader.load();
        ConsoleMoneyDialog moneyDialog = new ConsoleMoneyDialog();
        ConsoleCurrencyDialog currencyDialog = new ConsoleCurrencyDialog();
        ConsoleDateDialog dateDialog = new ConsoleDateDialog();
        ConsoleMoneyViewer moneyViewer = new ConsoleMoneyViewer();
        ExchangeRateLoader exchangeRateLoader = DBExchangeRateLoader.getInstance();
        ExchangeMoneyControl exchangeMoneyControl = new ExchangeMoneyControl(moneyDialog, currencyDialog, moneyViewer, exchangeRateLoader, dateDialog);
        exchangeMoneyControl.execute();
    }
}
