package com.priceminister.account;

public class NegativeAmountException extends Exception{
    private static final long serialVersionUID = -201909221645L;

    private Double amount;

    public NegativeAmountException(Double illegalAmount) {
        amount = illegalAmount;
    }

    public String toString() {return "Illegal negative amount: " + amount;}
}
