package com.ge.exercise3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {

    Bank bank;

    @Before
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void addAccountTest() {
        Account account = new Account("001");
        bank.addAccount(account);
        assertEquals(1, bank.numAccounts());
    }

    @Test
    public void getAccountTest() {
        Account account = new Account("002", "Checking", 100.0f);
        bank.addAccount(account);
        assertEquals(account, bank.getAccount("002"));
    }

    @Test
    public void depositToAccountTest() {
        Account account = new Account("003", "Checking", 100.0f);
        bank.addAccount(account);
        bank.depositToAccount("003", 100.0f);
        assertEquals(200.0f, account.getBalance(), 0.01);
    }

    @Test
    public void withdrawFromAccountTest() {
        Account account = new Account("004", "Checking", 100.0f);
        bank.addAccount(account);
        bank.withdrawFromAccount("004", 100.0f);
        assertEquals(0.0f, account.getBalance(), 0.01);
    }
    
    @Test
    public void getCurrentHoldingTest() {
    	Account acc1 = new Account("001");
        Account acc2 = new Account("002", "Checking", 100.0f);
        Account acc3 = new Account("003", "Checking", 100.0f);
        Account acc4 = new Account("004", "Checking", 100.0f);
        bank.addAccount(acc1);
        bank.addAccount(acc2);
        bank.addAccount(acc3);
        bank.addAccount(acc4);
        bank.depositToAccount("003", 100.0f);
        bank.withdrawFromAccount("004", 50.0f);
        
        float holding = bank.getCurrentHolding(bank.getAccountMap());
        System.out.println("holding == "+holding);
        assertEquals(350.0f, holding, 0.01);
	}
    
    @Test
    public void getProfitLossTest() {
    	Account acc1 = new Account("001");
        Account acc2 = new Account("002", "Checking", 100.0f);
        Account acc3 = new Account("003", "Checking", 100.0f);
        Account acc4 = new Account("004", "Checking", 100.0f);
        bank.addAccount(acc1);
        bank.addAccount(acc2);
        bank.addAccount(acc3);
        bank.addAccount(acc4);
        bank.depositToAccount("003", 100.0f);
        bank.withdrawFromAccount("004", 50.0f);
        
        acc2.setMonthlyFee(10.0f);
        acc3.setMonthlyFee(20.0f);
        acc4.setMonthlyFee(30.0f);

        
        float profitLoss = bank.getProfitLoss(bank.getAccountMap());
        System.out.println("profitLoss == "+profitLoss);
        assertEquals(60.0f, profitLoss, 0.01);
	}
}