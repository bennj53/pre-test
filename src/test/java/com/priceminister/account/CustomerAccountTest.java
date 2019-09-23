package com.priceminister.account;


import static org.junit.Assert.*;

import org.junit.*;

import com.priceminister.account.implementation.*;
import org.junit.rules.ExpectedException;


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

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

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
    public void testAddPositiveAmount() throws IllegalAmountException {
        customerAccount.add(100.99);
        Double balance = customerAccount.getBalance();

        assertEquals(100.99,balance,0);
    }

    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     */
    @Test
    public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException, IllegalAmountException {
        exceptionRule.expect(IllegalBalanceException.class);
        customerAccount.withdrawAndReportBalance(100.0, rule);
    }
    
    // Also implement missing unit tests for the above functionalities.

    /**
     * Tests Account constructor with parameter to add money to the account
     * and checks that the new balance is as expected.
     *
     */
    @Test
    public void testAccountCreationWithPositiveAmountAdded() throws IllegalAmountException {
        Account customerAccount = new CustomerAccount(500.00);
        Double balance = customerAccount.getBalance();

        assertEquals(500.00, balance, 0);
    }

    /**
     * Tests Account constructor with parameter to add negative amount to the account
     * and checks that an negative amount throws the expected exception.
     */
    @Test
    public void testAccountCreationWithNegativeAmountAdded() throws IllegalAmountException {
        exceptionRule.expect(IllegalAmountException.class);
        new CustomerAccount(-500.00);
    }

    /**
     * Tests Account constructor with parameter to add 0 amount to the account
     * and checks that the new balance is as expected.
     */
    @Test
    public void testAccountCreationWithZeroAmountAdded() throws IllegalAmountException {
        Account customerAccount = new CustomerAccount(0.0);
        Double balance = customerAccount.getBalance();

        assertEquals(0.0, balance, 0);
    }

    /**
     * Tests Account constructor with parameter to add null amount to the account
     * and checks that an null amount throws the expected exception.
     */
    @Test
    public void testAccountCreationWithNullAmountAdded() throws IllegalAmountException {
        exceptionRule.expect(IllegalAmountException.class);
        new CustomerAccount(null);
    }

    /**
     * Tests that adds an negative amount throws the expected exception.
     *
     */
    @Test
    public void testAddNegativeAmount() throws IllegalAmountException {
        exceptionRule.expect(IllegalAmountException.class);
        customerAccount.add(-100.00);
    }

    /**
     * Tests that adds zero to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddZeroAmount() throws IllegalAmountException {
        customerAccount.add(50.00);
        customerAccount.add(0.00);

        assertEquals(50.00, customerAccount.getBalance(),0);
    }

    /**
     * Tests that adds null amount throws the expected exception.
     *
     */
    @Test
    public void testAddNullAmount() throws IllegalAmountException {
        exceptionRule.expect(IllegalAmountException.class);
        customerAccount.add(null);
    }

    /**
     * Tests that withdraw a negative amount throws the expected exception.
     *
     */
    @Test
    public void testWithdrawNegativeAmountAndReportBalance() throws IllegalBalanceException, IllegalAmountException {
        exceptionRule.expect(IllegalAmountException.class);
        customerAccount.withdrawAndReportBalance(-100.0, rule);
    }

    /**
     * Tests that withdraw positive amount to the account
     * and checks that the new balance is as expected.
     *
     */
    @Test
    public void testWithdrawPositiveAmountAndReportBalance() throws IllegalBalanceException, IllegalAmountException {
        customerAccount.add(100.50);
        Double balance = customerAccount.withdrawAndReportBalance(50.25, rule);

        assertEquals(50.25,balance,0);
    }

    /**
     * Tests that withdraw zero to the account and checks that the new balance is as expected.
     */
    @Test
    public void testWithdrawZeroAmountAndReportBalance() throws IllegalBalanceException, IllegalAmountException {
        customerAccount.add(100.90);
        Double balance = customerAccount.withdrawAndReportBalance(0.0, rule);

        assertEquals(100.90,balance,0);
    }


    /**
     * Tests that withdraw null amount throws the expected exception.
     *
     */
    @Test
    public void testWithdrawNullAmount() throws IllegalBalanceException, IllegalAmountException {
        exceptionRule.expect(IllegalAmountException.class);
        customerAccount.add(100.50);
        customerAccount.withdrawAndReportBalance(null, rule);
    }


    /**
     *
     * Adds a lot of amounts with decimals to the account and checks that the new balance is as expected.
     */
    @Test
    public void testPrecisionAddPositiveAmount() throws IllegalAmountException {
        Double  cashInflow  =  1.2 ;
        Double expectedResult = 12.0 ;

        for ( int  i  =  0 ; i  <  10 ; i ++ ) {
            customerAccount.add(cashInflow);
        }
        Double balance = customerAccount.getBalance();

        assertEquals(expectedResult,balance,0.001);
        /*@TODO: 23/09/2019  :If necessary increase precision
                 ,using BigDecimal type instead of Double.
                 Warning, using BigDecimal reduce performance
        */

    }


}
