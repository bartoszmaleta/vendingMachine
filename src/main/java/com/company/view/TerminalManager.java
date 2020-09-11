package com.company.view;

import java.util.Scanner;

public class TerminalManager {
    Scanner sc = new Scanner(System.in);

    public int queryUserInt(String queryMessage){
        System.out.println(queryMessage);
        return sc.nextInt();
    }

    public int queryUserIntRange(String queryMessage, int inputMin, int inputMax){
        System.out.println(queryMessage);
        int response = sc.nextInt();
        if (response < inputMin && response > inputMax) {
            System.out.println("Not in range");
            System.out.println("Please stay within range " + inputMin + " and " + inputMax + ".");
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
        displayUserString("\n");
        displayUserString("       _____________________");
        displayUserString("       || Vending Machine ||");
        displayUserString("||================================||");
        displayUserString("||                                ||");
        displayUserString("||                                ||");
        displayUserString("||         Choose option:         ||");
        displayUserString("||                                ||");
        displayUserString("||                                ||");
        displayUserString("||    1. View Product List        ||");
        displayUserString("||    2. Purchase a Product       ||");
        displayUserString("||    3. Exit                     ||");
        displayUserString("||                                ||");
        displayUserString("||================================||\n");
    }
}
