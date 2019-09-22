package com.priceminister.account;


import static org.junit.Assert.*;

import org.junit.*;

import com.priceminister.account.implementation.*;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {
    
    Account customerAccount;
    AccountRule rule;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
        rule = new CustomerAccountRule();
    }
    
    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
        Double balance = customerAccount.getBalance();
        assertEquals(0.0, balance, 0);
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() throws NegativeAmountException{
        Double amount = 100.0000012345678910;
        customerAccount.add(amount);
        Double balance = customerAccount.getBalance();

        assertEquals(amount,balance,0);

    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     */
    @Test
    public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException, NegativeAmountException{
        exceptionRule.expect(IllegalBalanceException.class);
        //exceptionRule.expectMessage("Illegal account balance: ");

        Double amount = 100.0;
        customerAccount.withdrawAndReportBalance(amount, rule);

    }
    
    // Also implement missing unit tests for the above functionalities.

    /**
     * Tests Account constructor with parameter to add money to the account and checks that the new balance is as expected.
     *
     */
    @Test
    public void testAccountCreationWithAddedAmount() throws NegativeAmountException {
        Double addedAmount = 500.00;
        Account customerAccount = new CustomerAccount(addedAmount);
        Double balance = customerAccount.getBalance();

        assertEquals(addedAmount, balance, 0);
    }

    /**
     * Tests Account constructor with parameter to add money to the account and checks that the new balance is as expected.
     * Tests that an negative amount throws the expected exception.
     */
    @Test
    public void testAccountCreationWithIllegalAddedAmount() throws NegativeAmountException {
        exceptionRule.expect(NegativeAmountException.class);

        Double addedAmount = -500.00;
        Account customerAccount = new CustomerAccount(addedAmount);
    }

    /**
     * Tests that an negative amount throws the expected exception.
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddNegativeAmount() throws NegativeAmountException{
        exceptionRule.expect(NegativeAmountException.class);

        Double amount = -100.00;
        customerAccount.add(amount);

    }

    @Test
    public void testAddAmountZero() throws NegativeAmountException{
        Double amount = 0.00;
        customerAccount.add(amount);

        assertEquals(0.00, customerAccount.getBalance(),0);

    }

    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     */
    @Test
    public void testWithdrawAndReportBalanceNegativeAmount() throws IllegalBalanceException, NegativeAmountException{
        exceptionRule.expect(NegativeAmountException.class);

        Double amount = -100.0;
        Double balance = customerAccount.withdrawAndReportBalance(amount, rule);

    }

    @Test
    public void testWithdrawAndReportBalancePositiveAmount() throws IllegalBalanceException, NegativeAmountException{
        customerAccount.add(100.50);
        Double amount = 50.25;
        Double balance = customerAccount.withdrawAndReportBalance(amount, rule);

        assertEquals(50.25,balance,0);
    }

    @Test
    public void testWithdrawAndReportBalanceZero() throws IllegalBalanceException, NegativeAmountException{
        Double amount = 0.0;
        Double balance = customerAccount.withdrawAndReportBalance(amount, rule);

        assertEquals(amount,balance,0);
    }

    /*    *//**
     * Adds money to the account and checks that the new balance is as expected.
     *//*
    @Test
    public void testPrecisionAddPositiveAmount() {
        Double  cashInflow  =  1.2 ;

        for ( int  i  =  0 ; i  <  100 ; i ++ ) {
            customerAccount.add(cashInflow);
        }

        Double balance = customerAccount.getBalance();

        Double expectedResult = 120.0 ;

        assertEquals(expectedResult,balance,0);

    }*/


}
