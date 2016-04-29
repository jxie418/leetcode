package com.tiantian.assignment1;

public class Good {
	private String name;
	private int price;

	public Good() {
		this.name = "";
		this.price = 0;
	}

	public Good(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	public String getGoodString(int i) {
		return "("+i+")" + name +", worth " + "$"+price;
	}
}
