package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {

    TreeMap<String, MenuItem> menuItems;

    public TreeMap<String, MenuItem> getMenuItems() {
        //get the file to read from
        File file = new File("cateringsystem.csv");
        //initialize the TreMap (alphabetized) where the file will read to
        menuItems = new TreeMap<>();
        try {
            //instantiate a scanner that will read the file
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                //take each line from the file, split it by pipe character and create appropriate object
                //put object in the map with productCode as the key and the object as the value
                //index 0 in cateringsystem.csv designates MenuItem type
                //output grabs each line & assigns it to a variable
                String output = scanner.nextLine();
                //put each line of output in an array called fields
                String[] fields = output.split("\\|");
                //depending on the type of the item, we populate the constructor of each class with the appropriate fields
                if (fields[0].equalsIgnoreCase("a")) {
                    Appetizer appetizer = new Appetizer(fields[0], fields[1], fields[2], "You might need extra plates!", 25, Double.parseDouble(fields[3]));
                    menuItems.put(fields[1], appetizer);
                } else if (fields[0].equalsIgnoreCase("b")) {
                    Beverage beverage = new Beverage(fields[0], fields[1], fields[2], "Don't forget ice!",25, Double.parseDouble(fields[3]));
                    menuItems.put(fields[1], beverage);
                } else if (fields[0].equalsIgnoreCase("e")) {
                    Entree entree = new Entree(fields[0], fields[1], fields[2], "Did you remember dessert?", 25, Double.parseDouble(fields[3]));
                    menuItems.put(fields[1], entree);
                } else if (fields[0].equalsIgnoreCase("d")) {
                    Dessert dessert = new Dessert(fields[0], fields[1], fields[2], "Coffee goes with dessert (just saying.)" , 25, Double.parseDouble(fields[3]));
                    menuItems.put(fields[1], dessert);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.getCause();
        }
        return menuItems;
    }
}

