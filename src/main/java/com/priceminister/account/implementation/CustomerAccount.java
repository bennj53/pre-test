package com.priceminister.account.implementation;

import com.priceminister.account.*;


public class CustomerAccount implements Account {

    private Double balance;

    public CustomerAccount(){
        this.balance = 0.0;
    }

    public CustomerAccount(Double addedAmount) throws NegativeAmountException {
        this();
        this.add(addedAmount);
    }

    public void add(Double addedAmount) throws NegativeAmountException {
        if (addedAmount>=0){
            this.balance+=addedAmount;
        }else{
            throw new NegativeAmountException(addedAmount);
        }

    }

    public Double getBalance() {
        return balance;
    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) 
    		throws IllegalBalanceException, NegativeAmountException {

        if (withdrawnAmount>=0){
            Double newBalance = this.balance - withdrawnAmount;
            if (rule.withdrawPermitted(newBalance)){
                this.balance-= withdrawnAmount;
            }else{
                throw new IllegalBalanceException(newBalance);
            }
        }else{
            throw new NegativeAmountException(withdrawnAmount);
        }

        return this.balance;
    }

}
