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
        String resp = sc.next();
        return resp;
    }


    public void displayUserString(String userMessage) {
        System.out.println(userMessage);
    }
}
