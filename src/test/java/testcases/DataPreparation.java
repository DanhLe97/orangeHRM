package testcases;

import dataobjects.Employee;
import utils.helpers.DataFaker;

public class DataPreparation {

    public static Employee generateEmployee() {
        String firstname = DataFaker.generateRandomString(5);
        String midname = DataFaker.generateRandomString(5);
        String lastname = DataFaker.generateRandomString(5);
        String employeeId = DataFaker.generateRandomNumber(10);
        String username = DataFaker.generateRandomString(10);
        String password = DataFaker.generateRandomString(10);
        return new Employee(firstname, midname, lastname, employeeId, username, password, password);
    }


    public static Employee[] generateEmployees(int quantity) {
        Employee[] employees = new Employee[quantity];
        for (int i = 0; i < quantity; i++) {
            String firstname = "0"+DataFaker.generateRandomString(5);
            String midname = DataFaker.generateRandomString(5);
            String lastname = DataFaker.generateRandomString(5);
            String employeeId = DataFaker.generateRandomNumber(10);
            String username = DataFaker.generateRandomString(10);
            String password = DataFaker.generateRandomString(10);
            employees[i] = new Employee(firstname, midname, lastname, employeeId, username, password, password);
        }
        return employees;
    }

}
