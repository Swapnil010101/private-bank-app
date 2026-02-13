package com.bank.repository;

import com.bank.model.Account;
import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private Map<String, Account> accounts = new HashMap<>();

    public void save(Account account) {
        if (accounts.containsKey(account.getAccountNumber()))
            throw new IllegalArgumentException("Account already exists");
        accounts.put(account.getAccountNumber(), account);
    }

    public Account findById(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) throw new IllegalArgumentException("Account not found");
        return account;
    }
}
