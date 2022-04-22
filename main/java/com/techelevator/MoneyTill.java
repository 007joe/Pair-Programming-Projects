package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class MoneyTill {

    private double balance;
    //member variables that are CONSTANTS (static designation, meaning unchanging)
    private final static double ONE_HUNDRED = 100;
    private final static double FIFTY = 50;
    private final static double TWENTY = 20;
    private final static double TEN = 10;
    private final static double FIVE = 5;
    private final static double ONE = 1;
    private final static double QUARTER = 0.25;
    private final static double DIME = 0.10;
    private final static double NICKEL = 0.05;
    private final static double MAX_DEPOSIT_AMOUNT = 1500;
    HashMap<String, Integer> changeMap;
    CateringInventory cateringInventory = new CateringInventory();
//    Logger logger = new Logger();


    public MoneyTill(double balance) {
        this.balance = balance;
    }

    //this constructor is to provide Logger class with MoneyTill methods
    public MoneyTill() {
    }

    //deposit specified amount if it matches whole dollar amounts
    public double deposit(double depositAmount) {
        //check for deposit amount validity
        if (depositAmount == ONE || depositAmount == FIVE || depositAmount == TEN ||
                depositAmount == TWENTY || depositAmount == FIFTY || depositAmount == ONE_HUNDRED) {
            //TODO: bug where balance will increment over MAX_AMOUNT if difference between balance & MAX is less than 100 & 100 is deposited
            //deposits money if deposit amount < max, 1500
            if (balance < MAX_DEPOSIT_AMOUNT && balance + depositAmount < MAX_DEPOSIT_AMOUNT) {
                balance += depositAmount;
            }
        }
//        logger.logDeposit(depositAmount);
        return depositAmount;
    }

    //TODO: Discovered that log file indicates number of items purchased and their total, we would rewrite this makePurchase method with more time.
    //check if balance is greater than 0 && purchaseprice < balance
    //use method in shopping cart to compare the total from the cart to the customer balance
    public void makePurchase(String productCode) {
        if (balance > 0 && balance > cateringInventory.getInventory().get(productCode).getProductPrice()) {
            balance -= cateringInventory.getInventory().get(productCode).getProductPrice();
//            logger.logPurchase(cateringInventory.getInventory(), productCode);
        }
    }

    //while balance / 100 > 100, subtract 100 repeat until balance < 100
    //i represents the number of times the balance was divided by 100
    //i indicates how man 100 dollar bills we give back
    //reset i to 0
    //while balance / 50 > 50, subtract 50 repeat until balance < 50
    //i indicates how many 50 dollar bills we give back
    //reset i to 0
    // 250-> if we have an array [2, 1]
    // the array is like this [num 100 dollar bills, num 50 dollar bills, num 20 dollar bills, etc.]
    public HashMap<String, Integer> giveChange() {
        //create an arrayList
        changeMap = new HashMap<>();
        //use mod operator inside of a tracked for loop.
        // i is used to indicate the number of each piece of currency returned to the user when giveChange() method is called
        int i = 0;
        if (balance >= FIFTY) {
            while (balance % FIFTY <= FIFTY && balance >= FIFTY) {
                i++;
                balance -= FIFTY;
                BigDecimal temp = new BigDecimal(balance).setScale(2, RoundingMode.HALF_EVEN);
                balance = temp.doubleValue();
            }
            changeMap.put("Fifties", i);
            i = 0;
        } if (balance < FIFTY && balance >= TWENTY) {
            while (balance % TWENTY <= TWENTY && balance >= TWENTY) {
                i++;
                balance -= TWENTY;
                BigDecimal temp = new BigDecimal(balance).setScale(2, RoundingMode.HALF_EVEN);
                balance = temp.doubleValue();
            }
            changeMap.put("Twenties", i);
            i = 0;
        } if (balance < TWENTY && balance >= TEN) {
            while (balance % TEN <= TEN && balance >= TEN) {
                i++;
                balance -= TEN;
                BigDecimal temp = new BigDecimal(balance).setScale(2, RoundingMode.HALF_EVEN);
                balance = temp.doubleValue();
            }
            changeMap.put("Tens", i);
            i = 0;
        } if (balance < TEN && balance >= FIVE) {
            while (balance % FIVE <= FIVE && balance >= FIVE) {
                i++;
                balance -= FIVE;
                BigDecimal temp = new BigDecimal(balance).setScale(2, RoundingMode.HALF_EVEN);
                balance = temp.doubleValue();
            }
            changeMap.put("Fives", i);
            i = 0;
        } if (balance < FIVE && balance >= ONE) {
            while (balance % ONE <= ONE && balance >= ONE) {
                i++;
                balance -= ONE;
                BigDecimal temp = new BigDecimal(balance).setScale(2, RoundingMode.HALF_EVEN);
                balance = temp.doubleValue();
            }
            changeMap.put("Ones", i);
            i = 0;
        } if (balance < ONE && balance >= QUARTER) {
            while (balance % QUARTER <= QUARTER && balance >= QUARTER) {
                i++;
                balance -= QUARTER;
                BigDecimal temp = new BigDecimal(balance).setScale(2, RoundingMode.HALF_EVEN);
                balance = temp.doubleValue();
            }
            changeMap.put("Quarters", i);
            i = 0;
        } if (balance < QUARTER && balance >= DIME) {
            while (balance % DIME <= DIME && balance >= DIME) {
                i++;
                balance -= DIME;
                BigDecimal temp = new BigDecimal(balance).setScale(2, RoundingMode.HALF_EVEN);
                balance = temp.doubleValue();
            }
            changeMap.put("Dimes", i);
            i = 0;
        } if (balance < DIME) {
            while (balance >= NICKEL) {
                i++;
                balance -= NICKEL;
                BigDecimal temp = new BigDecimal(balance).setScale(2, RoundingMode.HALF_EVEN);
                balance = temp.doubleValue();
            }
            changeMap.put("Nickels", i);
        }
        //append the amount of times the tracker (i) increments to the array.
        //return array
        return changeMap;
    }

    public double getBalance() {
        return this.balance;
    }

    public HashMap<String, Integer> getChangeMap() {
        return changeMap;
    }

    public double setBalance(double amount) {
        balance += amount;
        return balance;
    }
}
