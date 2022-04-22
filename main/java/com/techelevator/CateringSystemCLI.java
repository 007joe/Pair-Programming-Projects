package com.techelevator;


import com.techelevator.view.UserInterface;

public class CateringSystemCLI {

    public static void main(String[] args) {
        CateringSystemCLI cli = new CateringSystemCLI();

        cli.run();

    }

    public void run() {
        UserInterface ui = new UserInterface();
        ui.menuHandler();
    }
}