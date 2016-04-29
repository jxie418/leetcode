package com.tiantian.assignment1;

import java.util.ArrayList;
import java.util.List;

public class Customer
{
    //Customer's Name;
    private String name;
    //credit of Customer.
    private int credit;
    //Good list of Customer.
    private List<Good> goodList;
    
    /**
     * Constructor for objects of class Customer
     */
    public Customer()
    {
        // initialise instance variables
        this.name="";
        this.credit = 0;
        this.goodList = new ArrayList<Good>();
    }
    
    public Customer(String name, int credit)
    {
        this.name = name;
        this.credit = credit;
        this.goodList = new ArrayList<Good>();
    }
    
    public void buyOneGood(Good good)
    {
       this.credit -=good.getPrice();
       this.goodList.add(good);
    }
    
    public void addCredits(int c) {
        this.credit += c;
    }
    
    public boolean canBuyGood(Good good) {
        return this.credit >= good.getPrice();
    }

    public String getName()
    {
        return this.name;
    }
    
    public int getCredit() 
    {
        return this.credit;
    }
    
    public List<Good> getGoodList() 
    {
        return this.goodList;
    }
}
