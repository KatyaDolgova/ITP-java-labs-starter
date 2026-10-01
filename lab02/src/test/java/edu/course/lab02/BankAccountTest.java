package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class BankAccountTest {

    @Test
    void constructor_throwsForNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1));
    }

    @Test
    void constructor_allowsZeroInitialBalance() {
        BankAccount account = new BankAccount(0);

        assertEquals(0, account.getBalance());
    }

    @Test
    void deposit_increasesBalance() {
        BankAccount account = new BankAccount(100);

        account.deposit(50);

        assertEquals(150, account.getBalance());
    }

    @Test
    void deposit_throwsForNonPositiveAmount() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
    }

    @Test
    void withdraw_decreasesBalanceOnSuccess() {
        BankAccount account = new BankAccount(100);

        account.withdraw(40);

        assertEquals(60, account.getBalance());
    }

    @Test
    void withdraw_throwsWhenAmountExceedsBalance() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(150));
    }

    @Test
    void withdraw_throwsForNonPositiveAmount() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
    }
}
