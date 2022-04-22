package com.techelevator.view;

import com.techelevator.*;

import java.util.*;

public class UserInterface {

    //instantiate all prerequisites for the UI
    private Scanner scanner;
    MoneyTill moneyTill = new MoneyTill(0);
    CateringInventory cateringInventory = new CateringInventory();
    ShoppingCart shoppingCart = new ShoppingCart();
//    Logger logger = new Logger();

    //set scanner to take user input
    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    //method that loops and displays options for seeing menu, ordering items, or exiting program
    public void menuHandler() {
        while (true) {
            printMainMenu();
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                printMenu(cateringInventory.getInventory());
            } else if (choice.equals("2")) {
                orderMenuHandler();
            } else if (choice.equalsIgnoreCase("q")) {
                System.out.println("Thank you for choosing Joe and Luke's catering! Buh-bye!");
                System.exit(0);
            } else {
                System.out.println("That wasn't a valid option. Please select one of the choices indicated.");
            }
        }
    }

    //this method is called in the above loop
    public void printMainMenu() {
        System.out.println("\n*********************************************");
        System.out.println("****** Joe and Luke's Gourmet Catering ******");
        System.out.println("*********************************************\n");
        System.out.println("Welcome! Please select an option below:");
        System.out.println("(1) Display Catering Items");
        System.out.println("(2) Order");
        System.out.println("(q) Quit");
    }

    //this method loops through user input and calls appropriate methods
    public void orderMenuHandler() {
        while (true) {
            //print the order menu
            printOrderMenu();
            String choice = scanner.nextLine();
            //choice 1 sends the user to the deposit money menu
            if (choice.equals("1")) {
                //stays in the deposit menu until user indicates they want to quit with "q"
                while (true) {
                    System.out.println("Please enter the preferred deposit amount (ex.. $1, $5, $10, $20, $50, $100");
                    System.out.println("When finished, press (q) to go back.");
                    System.out.println("\nCurrent Account Balance: " + String.format("%.2f", moneyTill.getBalance()));
                    String depositAmount = scanner.nextLine();
                    //if user indicates "q", menu returns to the previous loop (orderMenuHandler())
                    if (depositAmount.equalsIgnoreCase("q")) {
                        break;
                        //checks user input for validity and calls the deposit method in Money Till if input is valid
                    } else if (depositAmount.equals("1") || depositAmount.equals("5")
                            || depositAmount.equals("10") || depositAmount.equals("20")
                            || depositAmount.equals("50") || depositAmount.equals("100")) {
                        moneyTill.deposit(Double.parseDouble(depositAmount));
                        System.out.println("You've deposited amount: " + String.format("%.2f", Double.parseDouble(depositAmount)));
                    } else {
                        //if user input is invalid, indicate as such
                        System.out.println("\nYou can't deposit that!");
                    }
                }
                //choice 2 sends the user to the selectProducts menu (which has its own loop)
            } else if (choice.equals("2")) {
                selectProducts();
            }
            //choice 3 is like "checking out", shows receipt, and gives change back, then sends user to orderMenuHandler()
            if (choice.equals("3")) {
                printReceipt(shoppingCart.getCart());
                System.out.println("You receive $" + String.format("%.2f", moneyTill.getBalance()));
//                logger.logGiveChange();
                giveChangeHandler();
                break;
            }
        }
    }

    //submenu that provides additional options and displays customer balance
    public void printOrderMenu() {
        System.out.println("Please select an option:");
        System.out.println("(1) Add money");
        System.out.println("(2) Select/purchase product");
        System.out.println("(3) Complete Transaction");
        System.out.println("Current Account Balance: $" + String.format("%.2f", moneyTill.getBalance())); // add formatted account balance after implementing till
    }

    //prints receipt when user indicates they are done shopping
    public void printReceipt(TreeMap<String, MenuItem> cart) {
        //formatting for the header
        System.out.println(String.format("%5s %-7s %-20s %-8s %-8s %s", "Qty", "Type", "Description", "Price", "Total", "Message"));
        //"let me see everything in the map"
        for (Map.Entry<String, MenuItem> key : cart.entrySet()) {
            //formatting for each individual purchase
            System.out.println(String.format("%-5s %-7s %-20s %-8s %-8s %s", key.getValue().getQuantity(),
                    key.getValue().getProductName(), key.getValue().getProductDescription(),
                    String.format("$%.2f", key.getValue().getProductPrice()),
                    String.format("$%.2f", (key.getValue().getQuantity() * key.getValue().getProductPrice())),
                    key.getValue().getUpsaleMessage()));
        }
        //displays the total for all items in the cart
        System.out.println("Total: $" + String.format("%.2f", shoppingCart.calculateTotal(cart)));
    }

    //method to return change, lists all of the change in the format of the smallest amount of bills/coins
    public void giveChangeHandler() {
        //gives access to everything that's in the map
        //TODO: Items without values (0 nickels, 0 dollars, etc.) still print when calling getChange()
        for (Map.Entry<String, Integer> key : moneyTill.giveChange().entrySet()) {

            System.out.println(key.getValue() + " " + key.getKey());
        }
    }


    //prints the menu and decrements selected item, adds item to the cart
    //also validates user input against list of product codes
    public void selectProducts() {
        while (true) {
            printMenu(cateringInventory.getInventory());
            System.out.println("\nPlease select a menu item by Product Code: ");
            System.out.println("After selection of products enter (q) to return");
            String choice = scanner.nextLine();
            //quit this menu and return to previous menu loop
            if (choice.equalsIgnoreCase("q")) {
                break;
            } else if (cateringInventory.getProductCodes().contains(choice.toUpperCase())) {
                //check to see if balance is enough to purchase
                if (moneyTill.getBalance() >= cateringInventory.getInventory().get(choice.toUpperCase()).getProductPrice()) {
                    //if balance is sufficient, decrement inventory
                    cateringInventory.decrementInventory(cateringInventory.getInventory(), choice.toUpperCase());
                    //add item to cart
                    shoppingCart.addItem(choice.toUpperCase());
                    //subtract price from balance
                    moneyTill.makePurchase(choice.toUpperCase());
                } else {
                    //if balance < product price, tell customer insufficient funds
                    System.out.println("Insufficient funds.");
                }
            } else {
                //if input is invalid, indicate as such
                System.out.println("Not a valid product code.");
            }
        }
    }

    //gets all items from cateringsystem.csv and formats for readability, prints to system.out
    public void printMenu(TreeMap<String, MenuItem> menuMap) {
        //formatting for the header
        System.out.println(String.format("%-15s %-20s %-6s %s", "Product Code", "Description", "Qty", "Price"));
        //map provides access to all items in inventory, and allow for formatting
        //for loop says, "hey, let me see everything in the map"
        for (Map.Entry<String, MenuItem> key : menuMap.entrySet()) {
            //if the quantity is > 0, display the number
            if (key.getValue().getQuantity() > 0) {
                System.out.println(String.format("%-15s %-20s %-6s %s", key.getValue().getProductCode(),
                        key.getValue().getProductDescription(), key.getValue().getQuantity(), String.format("$%.2f", key.getValue().getProductPrice())));
            } else {
                //if the quantity is 0, display "SOLD OUT"
                System.out.println(String.format("%-15s %-20s %-6s %s", key.getValue().getProductCode(),
                        key.getValue().getProductDescription(), "SOLD OUT", String.format("$%.2f", key.getValue().getProductPrice())));
            }
        }
    }


}

