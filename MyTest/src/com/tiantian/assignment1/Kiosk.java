package com.tiantian.assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk
{
    private Customer customer;
    private Scanner scanner;
    private List<Good> listGoods;
    public Kiosk()
    {
        scanner = new Scanner(System.in);
        listGoods = new ArrayList<Good>();
        
        Good good1 = new Good("PEN,",10);
        listGoods.add(good1);
        
        Good good2 = new Good("BOOK", 20);
        listGoods.add(good2);
        
        Good good3 = new Good("DVD", 30);
        listGoods.add(good3);
        
        Good good4 = new Good("MOUSE", 40);
        listGoods.add(good4);
        
        Good good5 = new Good("KEYBOARD", 50);
        listGoods.add(good5);
        
    }
    
    
    // instance variables - replace the example below with your own
    public void printKioskMenus() 
    {
        System.out.println("Welcome to My Fantastic Kiosk");
        System.out.println("========================");
        System.out.println("(1) Create A New Order");
        System.out.println("(2) Buy More Credit");
        System.out.println("(3) Purchase An Item");
        System.out.println("(4) What Have I Ordered So Far?");
        System.out.println("(5) Collect My Order");
        System.out.println("(6) Display Help");
        System.out.println("(7) Leave Kiosk");
        System.out.println("Choose an option :");
    }
    
    public void createANewOrder() 
    {
        System.out.println("Enter your name please :");
        String name = scanner.next();
        System.out.println("Buy some credits please :");
        int credit = scanner.nextInt();
        customer = new Customer(name, credit);
        printKioskMenus();
    }
    
    public void buyMoreCredit() 
    {
        if (customer == null) {
             System.out.println("Please create A new Order first.");
             printKioskMenus();
        } else {
          System.out.println("Please enter credit you want to buy :");
          int moreCredit = scanner.nextInt();
          customer.addCredits(moreCredit);
          printKioskMenus();
        }
      
    }
    
    public void purcharseAnItem()
    {
    	System.out.println("These are the items available for sale today :");
    	System.out.println("----------------------------------------------");
    	for (int i = 0 ; i < listGoods.size(); i++) {
    		System.out.println(listGoods.get(i).getGoodString(i+1));
        }
    	System.out.println("(6) Let ME pick a random item for you!!!");
    	System.out.println();
    	System.out.println("Pick a number between 1-6 :");
    	
    }
}
