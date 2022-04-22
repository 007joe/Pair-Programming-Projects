package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;


import com.techelevator.tenmo.model.User;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("isAuthenticated()")
@RestController
public class SystemController {
//connecting all DAOS into the main controller
  @Autowired
  private UserDao userDao;
  @Autowired
  private TransferDao transferDao;
@Autowired
  private AccountDao accountDao;

//List user method thats find all users
  @RequestMapping(path = "/users", method = RequestMethod.GET)
  public List<User> listAllUsers() {
    return userDao.findAll();
  }

//balance method that gets current username associated with the current balance
@RequestMapping(path = "/accounts", method = RequestMethod.GET)
  public BigDecimal userBalance (Principal user) {
    long authenticatedUser = userDao.findIdByUsername(user.getName());
    return accountDao.getBalance(authenticatedUser);
  }
  //TODO build json that represents transfer request in POSTMAN, (raw body)
  //insert into transfertable on PGADMIN
  //transaction method that creates a transfer transaction
  @ResponseStatus(HttpStatus.CREATED)
@RequestMapping(path = "/transfers", method = RequestMethod.POST)
  public Transfer fundsTransfer(@RequestBody Transfer transfer, Principal principal) {
  long senderId = userDao.findIdByUsername(principal.getName());
  return transferDao.createTransfer(senderId, transfer);
}

//Transfer method that grabs a specific transfer by its ID
 /* @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(path = "/transfers/{id}", method = RequestMethod.GET)
  public Transfer transferById(@PathVariable Long transferId) {
    Transfer transferDetailsById = new Transfer();
    transferDetailsById = transferDao.getTransferById(transferId);
    return  transferDetailsById;
  }*/

  //transfer method that provides a list of all users to make a transaction with
@ResponseStatus(HttpStatus.NO_CONTENT)
@RequestMapping(path = "/transfers", method = RequestMethod.GET)
public List<Transfer> listOfUsers(Principal principal) {
    Long accountId = accountDao.getAccountId(String.valueOf(userDao.findIdByUsername(principal.getName())));
    return transferDao.getAllTransfers(accountId);
}

}
