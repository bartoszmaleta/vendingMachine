package com.company;

import com.company.controller.Controller;
import com.company.dao.CoinDao;
import com.company.dao.CoinDaoImpl;
import com.company.dao.ProductDao;
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
        Controller controller = new Controller(new CoinDaoImpl(), new ProductDaoImpl());
        controller.run();
    }
}
