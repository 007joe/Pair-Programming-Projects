package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTransferDao implements TransferDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //SQL method to get all transfer by Account ID
    @Override
    public List<Transfer> getAllTransfers(Long accountId) {
        List<Transfer> listOfTransfers = new ArrayList<>();
        String sql = "SELECT *, transfer_type_desc, transfer_status_desc" +
                "FROM transfer" +
                "JOIN transfer_type ON transfer.transfer_type_id = transfer_type.transfer_type_id" +
                "JOIN transfer_status ON transfer_status_id = transfer_status.transfer_status_id" +
                "WHERE transfer.account_from = ? OR transfer.account_to = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Transfer transfers = mapRowToTransfer(results);
            listOfTransfers.add(transfers);
        }
        return listOfTransfers;
    }
    //TODO 5 Queries
    //SQL method to create a new transfer and return the ID of the newly created transfer
    @Override
    public Transfer createTransfer(long senderId, Transfer transfer) {
        /* Step 1: Take sender ID and lookup senderId's account (Account From) Select Query
        Step 2: take receiverID (in transfer object) and lookup there account Number (Account To) plug into insert line 43,

         */
        String sql = "INSERT INTO transfer(transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount" +
                "values (2, 2, ?, ?, ?, ?) RETURNING transfer_id;";
        Integer transferId = jdbcTemplate.queryForObject(sql, Integer.class, transfer.getAccountFrom(
        ), transfer.getAccountTo(), transfer.getAmount());
        transfer.setTransferId(transferId);
        // two update queries to update balance. debit balance from accountfrom and add to account TO
        return transfer;
    }

    //SQL method that grabs a transfer by account ID and returns transferID
 /*   @Override
    public Transfer getTransferById(Long accountId) {
        String sql = "SELECT * from transfer WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);

        return transferById(accountId);
    }*/

//maprow that sets all results to a transfer object
    private Transfer mapRowToTransfer(SqlRowSet results){
    Transfer transfer = new Transfer();
    transfer.setTransferId(results.getInt("transfer_id"));
    transfer.setAmount(results.getBigDecimal("amount"));
    transfer.setAccountFrom(results.getLong("account_from"));
    transfer.setAccountTo(results.getLong("account_to"));
    transfer.setTransferStatusDesc(results.getString("transfer_status_desc"));
    transfer.setTransferTypeId(results.getLong((int) results.getLong("transfer_status_id")));
    transfer.setTransferStatusDesc(results.getString("transfer_type_desc"));
    transfer.setTransferTypeId(results.getLong("transfer_type_id"));
    return transfer;
  }



}
