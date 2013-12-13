package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Currency;
import model.ExchangeRate;
import model.Number;
import oracle.jdbc.OracleDriver;

public class DBExchangeRateLoader implements ExchangeRateLoader{
    
    private static DBExchangeRateLoader instance;
     
    private DBExchangeRateLoader(){
    }
     
    public static DBExchangeRateLoader getInstance() {
        if (instance == null) 
            instance = new DBExchangeRateLoader();
        return instance;
    }
    
    @Override
    
    public ExchangeRate load(Currency fromCurrency, Currency toCurrency){
        return load(new Date(), fromCurrency, toCurrency);
    }

    @Override
    public ExchangeRate load(Date date, Currency fromCurrency, Currency toCurrency){
        String userName = "system";
        String password = "orcl";
        String server = "localhost:100:orcl";
        try {
            DriverManager.registerDriver(new OracleDriver());
            // jdbc:oracle:thin:@server,userName,password
            Connection connection;
        connection = DriverManager.getConnection
        ("jdbc:orcl:thin:@" + server, userName, password);
        Statement statement;
        statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from CAMBIO_EUR_A");
        while (set.next()){
            if (set.getString("DIVISA").equals(fromCurrency.getCode()))
                return new ExchangeRate(date, fromCurrency, toCurrency, new Number(set.getFloat(fromCurrency.getCode())) {});    
        }
        }
        catch (SQLException ex) {
        }
        return null;
    }
}    



/*public class Kataoracle {

    public static void main(String[] args) throws SQLException{
        String userName = "system";
        String password = "orcl";
        String server = "localhost:100:orcl";
        DriverManager.registerDriver(new OracleDriver());
        // jdbc:oracle:thin:@server,userName,password
        Connection connection = DriverManager.getConnection
                ("jdbc:orcl:thin:@" + server, userName, password);
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from CAMBIO_EUR_A");
        while (set.next()){
            printRegister(set);
        }
    }

    private static void printRegister(ResultSet set) throws SQLException {
        System.out.println(set.getString("DIVISA"));
        System.out.println(set.getBigDecimal("CAMBIO"));
    }
}*/

