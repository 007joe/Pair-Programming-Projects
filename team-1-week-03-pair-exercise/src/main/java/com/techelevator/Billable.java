package com.techelevator;

import java.util.HashMap;
import java.util.Map;

public interface Billable {
    private double getBalanceDue(Map<String, Double> servicesRendered) {
        Map<String, Double> servicesOffered = new HashMap<>();
        servicesOffered.put("Grooming", 20.00);
        servicesOffered.put("Walking", 30.00);
        servicesOffered.put("Sitting", 15.00);
        return getBalanceDue();
    }

    double getBalanceDue();
    }