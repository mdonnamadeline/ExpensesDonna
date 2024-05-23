package com.example.expensemanager;

public class Expense {
    private int id;
    private String item;
    private String amount;

    public Expense() {
    }

    public Expense(int id, String item, String amount) {
        this.id = id;
        this.item = item;
        this.amount = amount;
    }

    public Expense(String item, String amount) {
        this.item = item;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public String getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return  item + "\t" + amount;
    }
}
