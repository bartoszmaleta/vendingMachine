package com.company;

import com.company.controller.Controller;
import com.company.dao.CoinDaoImpl;
import com.company.dao.ProductDaoImpl;
import com.company.view.ConsoleIO;

import java.text.DecimalFormat;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ProductDaoImpl prodDAO = new ProductDaoImpl();
        CoinDaoImpl coinDAO = new CoinDaoImpl();
        DecimalFormat df = new DecimalFormat();
        ConsoleIO cons = new ConsoleIO();

        Controller controller = new Controller(cons, coinDAO, prodDAO, df);
        controller.run();
    }
}
