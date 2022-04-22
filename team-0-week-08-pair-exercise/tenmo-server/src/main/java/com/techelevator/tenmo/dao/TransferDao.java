package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
//List all transfer
List<Transfer> getAllTransfers(Long accountId);

//create a transfer
Transfer createTransfer(long senderId, Transfer transfer);

//Finding a transfer by its ID
/*
Transfer getTransferById(Long transferId);

*/


}
