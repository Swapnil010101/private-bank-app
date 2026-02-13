package com.bank.service;

import com.bank.model.Account;
import com.bank.repository.AccountRepository;

public class BankService {

    private AccountRepository repository;

    public BankService(AccountRepository repository) {
        this.repository = repository;
    }

    public void createAccount(String accNo, String name, double balance) {
        Account account = new Account(accNo, name, balance);
        repository.save(account);
    }

    public void deposit(String accNo, double amount) {
        Account account = repository.findById(accNo);
        account.deposit(amount);
    }

    public void withdraw(String accNo, double amount) {
        Account account = repository.findById(accNo);
        account.withdraw(amount);
    }

    public void transfer(String from, String to, double amount) {
        if (from.equals(to))
            throw new IllegalArgumentException("Cannot transfer to same account");

        Account sender = repository.findById(from);
        Account receiver = repository.findById(to);

        sender.withdraw(amount);
        receiver.deposit(amount);
    }

    public double checkBalance(String accNo) {
        return repository.findById(accNo).getBalance();
    }
}
