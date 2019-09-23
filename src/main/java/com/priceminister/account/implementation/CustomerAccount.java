package com.priceminister.account.implementation;

import com.priceminister.account.*;


public class CustomerAccount implements Account {

    private Double balance;

    public CustomerAccount(){
        this.balance = 0.0;
    }

    public CustomerAccount(Double addedAmount) throws IllegalAmountException {
        this();
        this.add(addedAmount);
    }

    public void add(Double addedAmount) throws IllegalAmountException {
        if (addedAmount != null && addedAmount>=0){
            this.balance+=addedAmount;
        }else{
            throw new IllegalAmountException(addedAmount);
        }

    }

    public Double getBalance() { return balance; }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) 
    		throws IllegalBalanceException, IllegalAmountException {

        if (withdrawnAmount != null && withdrawnAmount>=0){
            Double newBalance = this.balance - withdrawnAmount;
            if (rule.withdrawPermitted(newBalance)){
                this.balance-= withdrawnAmount;
            }else{
                throw new IllegalBalanceException(newBalance);
            }
        }else{
            throw new IllegalAmountException(withdrawnAmount);
        }

        return this.balance;
    }

}
