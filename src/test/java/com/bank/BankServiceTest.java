package com.bank;

import com.bank.repository.AccountRepository;
import com.bank.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {
    private BankService bankService;

    @BeforeEach
    void setup() {
        AccountRepository repository = new AccountRepository();
        bankService = new BankService(repository);
        bankService.createAccount("101", "John", 1000);
        bankService.createAccount("102", "Alice", 2000);
    }

    @Test
    void testDeposit() {
        bankService.deposit("101", 500);
        assertEquals(1500, bankService.checkBalance("101"));
    }

    @Test
    void testWithdraw() {
        bankService.withdraw("101", 300);
        assertEquals(700, bankService.checkBalance("101"));
    }

    @Test
    void testInsufficientBalance() {
        assertThrows(IllegalStateException.class, () -> bankService.withdraw("101", 5000));
    }

    @Test
    void testTransfer() {
        bankService.transfer("101", "102", 500);
        assertEquals(500, bankService.checkBalance("101"));
        assertEquals(2500, bankService.checkBalance("102"));
    }

    @Test
    void testTransferToSameAccount() {
        assertThrows(IllegalArgumentException.class, () -> bankService.transfer("101", "101", 100));
    }
}
