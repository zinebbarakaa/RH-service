package com.giantLink.RH.services.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.services.EmployeeService;

import jakarta.transaction.Transactional;

import com.giantLink.RH.exceptions.RessourceNotFoundException;
import com.giantLink.RH.models.request.EmployeeRequest;
import com.giantLink.RH.models.response.EmployeeResponse;
import com.giantLink.RH.exceptions.InvalidInputException;

@Service
@Transactional // Active la gestion des transactions pour toutes les méthodes de la classe

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse add(EmployeeRequest request) {
        // TODO: Implement the logic to add an employee
        // For example, you can create a new Employee entity and save it to the repository
        // If the request data is invalid, throw InvalidInputException

       // if (request.getFirstName() == null || request.getFirstName().isEmpty()
        //        || request.getLastName() == null || request.getLastName().isEmpty()
        //        || request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new InvalidInputException("Invalid input data. All fields are required.");
        //}

        // Implement the saving logic here and return the EmployeeResponse object
       // return null;
    }

    @Override
    public List<EmployeeResponse> get() {
        // TODO: Implement the logic to retrieve all employees
        // If there are no employees in the database, throw RessourceNotFoundException
      //  List<EmployeeResponse> employees = // Retrieve employees from the repository

       // if (employees.isEmpty()) {
            throw new RessourceNotFoundException("Employees", "list", "No employees found.");
       // }

       // return employees;
    }

    @Override
    public EmployeeResponse update(EmployeeRequest request, Long id) {
        // TODO: Implement the logic to update an employee by ID
        // If the employee with the given ID does not exist, throw RessourceNotFoundException
        //EmployeeResponse existingEmployee = // Retrieve the employee from the repository by ID

        //if (existingEmployee == null) {
            throw new RessourceNotFoundException("Employee", "id", String.valueOf(id));
      // }

        // Implement the updating logic here and return the updated EmployeeResponse object
       // return null;
    }

    @Override
    public void delete(Long id) {
        // TODO: Implement the logic to delete an employee by ID
        // If the employee with the given ID does not exist, throw RessourceNotFoundException
       // EmployeeResponse existingEmployee = // Retrieve the employee from the repository by ID

        //if (existingEmployee == null) {
            throw new RessourceNotFoundException("Employee", "id", String.valueOf(id));
       // }

        // Implement the deletion logic here
    }

    @Override
    public EmployeeResponse get(Long id) {
        // TODO: Implement the logic to retrieve an employee by ID
        // If the employee with the given ID does not exist, throw RessourceNotFoundException
        //EmployeeResponse employee = // Retrieve the employee from the repository by ID

        //if (employee == null) {
            throw new RessourceNotFoundException("Employee", "id", String.valueOf(id));
      //  }

       // return employee;
    }
}
