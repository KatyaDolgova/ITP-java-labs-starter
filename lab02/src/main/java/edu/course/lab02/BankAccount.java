package edu.course.lab02;

public class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
        }

        this.balance = balance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть только положительной");
        }

        this.balance += amount;
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть только положительной");
        }

        if (amount > this.balance) {
            throw new IllegalArgumentException("Сумма списания не может превышать остаток");
        }

        this.balance -= amount;
    }

    public int getBalance(){
        return this.balance;
    }
}
