package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    //Get account ID
    Long getAccountId(String userName);

    //TODO fix userId method
  //  Account getUserId(Long userId);

    //getBalance
   BigDecimal getBalance(Long userId);

   //Withdrawal method
    boolean withdrawal (Account account, BigDecimal funds);
    //Deposit Method
    void deposit (Account account, BigDecimal funds);







}
