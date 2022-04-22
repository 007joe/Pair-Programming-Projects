package com.techelevator.crm;

import com.techelevator.hr.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Pet {

    private String name;
    private String species;
    private List<String>  vaccinations = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public List<String> getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(List<String> vaccinations) {
        this.vaccinations = vaccinations;
    }

    public Pet(String name, String species){
        this.name=name;
        this.species=species;
    }

    public String listVaccinations() {            //TODO: take list items into new String - not certain this is right

//        List<String> vaccinations = new ArrayList<>();
//        vaccinations.add("Rabies");
//        vaccinations.add("Distemper");
//        vaccinations.add("Parvo");

//        int i = 0;
//        for(i = 0; i < vaccinations.size() -1; i++) {
//            System.out.println(vaccinations.get(i));
////            Set<String> petVax= (vaccinations.get(i)+", ");
//        }
        String[] vaccinations={"Rabies", "Distemper", "Parvo"};
        String listString=String.join(", ", vaccinations);
        return listString;

//        Set<String> petVax= (vaccinations.get(i)+", ");

//        for(String vaccine : vaccinations)          //
//            System.out.println(vaccine.toString());
    }
}
