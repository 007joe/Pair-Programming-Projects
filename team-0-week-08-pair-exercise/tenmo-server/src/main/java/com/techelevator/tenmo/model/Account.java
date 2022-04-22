package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {

    public Account() { }

    private Long accountId;
    private Long userId;
    private BigDecimal balance;
    private BigDecimal funds;

    public Account(Long accountId, Long userId, BigDecimal balance, BigDecimal funds) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
        this.funds = funds;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void deposit(BigDecimal funds) {
    }

    public boolean withdrawal(BigDecimal funds) {
        return true;
    }
}
