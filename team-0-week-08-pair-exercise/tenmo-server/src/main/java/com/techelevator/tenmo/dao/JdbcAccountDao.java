package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override

    //get accountID method thats grabs an ID based off the userName input
    public Long getAccountId(String userName) {
        Long accountId = 0L;
        String sql = "SELECT account * username from account join tenmo_user on account.user_id = tenmo_user.user_id where account.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
        accountId = results.getLong("account_id");
        return accountId;

    }
//Incorrect Method
/*    @Override
    public Account getUserId(Long userId) {
        Account account = null;
        String sql = "select account *, username from account join tenmo_user on account.user_id = tenmo_user.user_id where account.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        account = mapRowToAccount(results);
        return account;
    }*/
//TODO take these query strings and move them to Transfer
    //getBalance method that selects balanced based on userID
    @Override
    public BigDecimal getBalance(Long userId) {
     String sql = "SELECT balance FROM account WHERE user_id = ?;";
             BigDecimal usersTotalbalance = jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);
             return usersTotalbalance;
    }
//TODO send to transfer
    //withdrawal method, updates balance everytime a withdrawal is made.
    @Override
    public boolean withdrawal(Account account, BigDecimal funds) {
        if (account.withdrawal(funds)){
            String sql = "update account set balance = ? where account_id = ?;";
            jdbcTemplate.update(sql, account.getBalance(), account.getAccountId());
            return true;
        }
        return false;
    }
//TODO send to transfer
    //deposit method, update balance everytime a deposit is made.
    @Override
    public void deposit(Account account, BigDecimal funds) {
        account.deposit(funds);
        String sql = "update account set balance = ? where account_id = ?;";
        jdbcTemplate.update(sql, account.getBalance(), account.getAccountId());

    }

// MapRow methods that sets results into account object
    Account mapRowToAccount(SqlRowSet results) {
        Account account = new Account();
        if(results.next()){
           account.setAccountId(results.getLong("account_id"));
           account.setUserId(results.getLong("user_id"));
           account.setBalance(results.getBigDecimal("balance"));
        }
        return account;
    }


}
