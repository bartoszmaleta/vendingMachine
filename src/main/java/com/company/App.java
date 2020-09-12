package com.company;

import com.company.controller.Controller;
import com.company.dao.CoinDaoImpl;
import com.company.dao.ProductDaoImpl;

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

// TODO: catch exception about not enough coins in machine!
