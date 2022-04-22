package com.techelevator.crm;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PetTests {

    private Pet pet1=new Pet("Harvey", "Rabbit");

    @Test
    public void does_vaccine_list_return_comma_string() {
        Assert.assertEquals("Rabies, Distemper, Parvo", pet1.listVaccinations());
    }


}
