package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    private List<Department> departments;
    private List<Employee> employees;
    private Map<String,Project> projects = new HashMap<>();

    /**
     * The main entry point in the application
     * @param args
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    private void run() {
        // create some departments
        createDepartments();

        // print each department by name
        printDepartments();

        // create employees
        createEmployees();

        // give Angie a 10% raise, she is doing a great job!

        // print all employees
        printEmployees();

        // create the TEams project
        createTeamsProject();
        // create the Marketing Landing Page Project
        createLandingPageProject();

        // print each project name and the total number of employees on the project
        printProjectsReport();
    }

    /**
     * Create departments and add them to the collection of departments
     */
    private void createDepartments() {
        this.departments = new ArrayList<Department>();
        departments.add(new Department(1,"Marketing"));
        departments.add(new Department(2,"Sales"));
        departments.add(new Department(3,"Engineering"));
    }

    /**
     * Print out each department in the collection.
     */
    private void printDepartments() {
        System.out.println("------------- DEPARTMENTS ------------------------------");
        for(Department d : departments) {
            System.out.println(d.getName());
        }
    }

    /**
     * Create employees and add them to the collection of employees
     */
    private void createEmployees() {
        this.employees = new ArrayList<Employee>();
        Employee dean = new Employee();
        dean.setEmployeeId(1);
        dean.setFirstName("Dean");
        dean.setLastName("Johnson");
        dean.setEmail("djohnson@teams.com");
        dean.setDepartment(departments.get(2));
        dean.setHireDate("08/21/2020");
        employees.add(dean);
        employees.add(new Employee(2,"Angie","Smith","asmith@teams.com",this.departments.get(2),"08/21/2020"));
        employees.add(new Employee(3, "Margaret", "Thompson", "mthompson@teams.com", this.departments.get(0), "08/21/2020"));
        employees.get(1).raiseSalary(10.0);
    }


    /**
     * Print out each employee in the collection.
     */
    private void printEmployees() {
        System.out.println("\n------------- EMPLOYEES ------------------------------");
        for (Employee e : employees) {
            System.out.println(e.getFullName() + " (" + e.getSalary()+ ") " + e.getDepartment().getName());
        }
    }

    /**
     * Create the 'TEams' project.
     */
    private void createTeamsProject() {
        Project teamsProject = new Project("TEams","Project Management Software","10/10/2020","11/10/2020");
        for(Employee e : this.employees) {
            if(e.getDepartment().getName().equals("Engineering")) {
                teamsProject.addTeamMember(e);
            }
        }
        projects.put("TEams",teamsProject);
    }

    /**
     * Create the 'Marketing Landing Page' project.
     */
    private void createLandingPageProject() {
        Project landingPageProject = new Project("Marketing Landing Page", "Lead Capture Landing Page for Marketing", "10/10/2020", "10/17/2020");
        for(Employee e : this.employees) {
            if(e.getDepartment().getName().equals("Marketing")) {
                landingPageProject.addTeamMember(e);
            }
        }
        projects.put("Marketing Landing Page", landingPageProject);
    }

    /**
     * Print out each project in the collection.
     */
    private void printProjectsReport() {
        System.out.println("\n------------- PROJECTS ------------------------------");
        System.out.println(projects.get("TEams").getName() + ": " + projects.get("TEams").getTeamMembers().size());
        System.out.println(projects.get("Marketing Landing Page").getName() + ": " + projects.get("Marketing Landing Page").getTeamMembers().size());
    }

}
