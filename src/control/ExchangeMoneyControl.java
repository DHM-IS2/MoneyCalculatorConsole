package control;

import java.io.IOException;
import model.Currency;
import model.ExchangeRate;
import model.Money;
import model.MoneyExchanger;
import persistence.ExchangeRateLoader;
import ui.ConsoleCurrencyDialog;
import ui.ConsoleMoneyDialog;
import ui.ConsoleMoneyViewer;

public class ExchangeMoneyControl{
    
    private ConsoleMoneyDialog moneyDialog;
    private ConsoleCurrencyDialog currencyDialog;
    private ConsoleMoneyViewer moneyViewer;
    private ExchangeRateLoader exchangeRateLoader;

    public ExchangeMoneyControl(ConsoleMoneyDialog moneyDialog, ConsoleCurrencyDialog currencyDialog, ConsoleMoneyViewer moneyViewer, ExchangeRateLoader exchangeRateLoader) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.moneyViewer = moneyViewer;
        this.exchangeRateLoader = exchangeRateLoader;
    }
    
    public void execute() throws IOException{
        Money money = readMoney();
        Currency currency = readCurrency();
        ExchangeRate exchangeRate = exchangeRateLoader.load(money.getCurrency(), currency);
        money = MoneyExchanger.exchange(money, exchangeRate);
        moneyViewer.setMoney(money);
        moneyViewer.show();
    }
    
    private Money readMoney() throws IOException{
        moneyDialog.execute();
        return moneyDialog.getMoney();
    }
    
    private Currency readCurrency() throws IOException{
        currencyDialog.execute();
        return currencyDialog.getCurrency();
    }
}
