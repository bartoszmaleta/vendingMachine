package com.company.view;

import java.util.Scanner;

public class ConsoleIO {
    Scanner sc = new Scanner(System.in);

    public int queryUserInt(String queryMessage){
        System.out.println(queryMessage);
        return sc.nextInt();
    }

    public int queryUserIntRange(String queryMessage, int inputMin, int inputMax){
        System.out.println(queryMessage);
        int response = sc.nextInt();
        if (response < inputMin && response > inputMax) {
            System.out.println("Looks like you are out of range, try again!");
            System.out.println("Please stay within " + inputMin + " and " + inputMax + ".");
            System.out.println(queryMessage);
            response = sc.nextInt();
        }
        return response;
    }

    public String queryUserString(String queryMessage){
        System.out.println(queryMessage);
        return sc.next();
    }

    public void displayUserString(String userMessage) {
        System.out.println(userMessage);
    }

    public void displayMenu() {
        displayUserString("\n**********************************");
        displayUserString("*         Welcome to the         *");
        displayUserString("*        Vending Machine!        *");
        displayUserString("*                                *");
        displayUserString("*        -- Main Menu --         *");
        displayUserString("*                                *");
        displayUserString("*       Would you like to:       *");
        displayUserString("*    1. View Product List        *");
        displayUserString("*    2. Purchase a Product       *");
        displayUserString("*    3. Exit                     *");
        displayUserString("*                                *");
        displayUserString("**********************************\n");
    }
}
