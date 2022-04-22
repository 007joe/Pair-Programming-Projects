package com.techelevator;

public class Department {
    // instance variables
    private int departmentId;
    private String name;

    //Constructor v

    public Department(int departmentId, String name) {
        this.departmentId = departmentId;
        this.name = name;
    }
//int getter v
    public int getDepartmentId() {
        return departmentId;
    }
// int set v
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
// string getter v
    public String getName() {
        return name;
    }
   // string setter v
    public void setName(String name) {
        this.name = name;
    }
}
