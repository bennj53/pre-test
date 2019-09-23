package com.priceminister.account;

public class IllegalAmountException extends Exception{
    private static final long serialVersionUID = -201909221645L;

    private Double amount;

    public IllegalAmountException(Double illegalAmount) {
        amount = illegalAmount;
    }

    public String toString() {return "Illegal amount: " + amount;}
}
