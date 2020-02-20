package com.ge.exercise3;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private static final Logger logger = LogManager.getLogger(Bank.class);
    private Map<String, Account> accountMap;

    public Bank() {
        accountMap = new HashMap<>();
    }

    public Account getAccount(String accountNumber) {
        return accountMap.get(accountNumber);
    }

    public void addAccount(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }

    public void depositToAccount(String accountNumber, float amount) {
        getAccount(accountNumber).deposit(amount);
    }

    public void withdrawFromAccount(String accountNumber, float amount) {
        getAccount(accountNumber).withdraw(amount);
    }

    public int numAccounts() {
        return accountMap.size();
    }
    
    //Get a sum of current holdings
    public float getCurrentHolding(Map<String, Account> am) {
    	float res = 0.0f;
    	for(Map.Entry<String, Account> entry : am.entrySet()) {
    		res += entry.getValue().getBalance();
    	}
    	return res;
    }
    
    public Map<String, Account> getAccountMap() {
    	return accountMap;
    }
    
    //Project if the bank will produce a profit or loss in the next month based on fees collected on each account vs interest paid out
    public float getProfitLoss(Map<String, Account> am) {
    	float res = 0.0f;
    	for(Map.Entry<String, Account> entry : am.entrySet()) {
    		Account account = entry.getValue();
    		res += account.getBalance() - account.valueNextMonth();
    	}
    	if(res>0){
    		logger.log(Level.ERROR, "Bank will produce profit of "+res +" for next month");
    	}else{
    		logger.log(Level.ERROR, "Bank will produce loss of "+res +" for next month");
    	}
    	return res;
    }
   
}
