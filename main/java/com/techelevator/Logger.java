package com.techelevator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TreeMap;

public class Logger {
//
//    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//    MoneyTill moneyTill = new MoneyTill();
//
//    public Logger() {
//
//    }
//
////    String home = System.getProperty("user.home");
////    Path path = Paths.get(home, "my", "app", "dir");
////    boolean directoryExists = Files.exists(path);
////        if(directoryExists)
////        File file = new File(path, "logFile.txt");
////        file.createNewFile();
//
//
//
//    //create a directory
//    //create a file for logging
//    //instantiate a PrintWriter that appents, rather than rewrites
//    //log items in the file accordint to their type
//    //eg DEPOSIT MONEY, MAKE PURCHASE, GIVE CHANGE
//    //log should include date, time, action taken (as above), and new customer balance
//
//    public void log(String message) {
//        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("logFile.txt", true)))) {
//            out.println(message);
//            out.flush();
//        } catch (Exception e) {
//            e.getCause();
//        }
//    }
//
//    public void logDeposit(double depositAmount) {
//        log(timeFormat.format(timestamp) + " ADD MONEY: " + String.format("$%.2f", depositAmount) + " " + String.format("$%.2f", moneyTill.getBalance()));
//    }
//
//    public void logPurchase(TreeMap<String, MenuItem> inventory, String productCode) {
//            log(timeFormat.format(timestamp) + " " +
//                    String.format("%s %s", inventory.get(productCode).getProductDescription(), productCode) +
//                    " " + String.format("$%.2f", inventory.get(productCode).getProductPrice()) + " " +
//                    String.format("$%.2f", moneyTill.getBalance()));
//    }
//
//    public void logGiveChange() {
//            log(timeFormat.format(timestamp) + " GIVE CHANGE: " + String.format("$%.2f", moneyTill.getBalance()) + " $0.00");
//    }

}


