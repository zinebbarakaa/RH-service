package com.giantLink.RH.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giantLink.RH.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
