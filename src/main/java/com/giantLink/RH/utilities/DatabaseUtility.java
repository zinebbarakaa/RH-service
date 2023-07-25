package com.giantLink.RH.utilities;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Component
public class DatabaseUtility {
    @Autowired
    EmployeeRepository employeeRepository;

    public void initDatabase(){
        Logger.getLogger("Database utility").info("Seeding database ...");
        initEmployees();
        Logger.getLogger("Database utility").info("Database seeding complete");
    }

    public void initEmployees(){
//        Check table is empty
        if (employeeRepository.count() > 0) return;

        Employee employee1 = Employee.builder()
                .firstName("anass")
                .lastName("el yaagoubi")
                .cin("cd256841")
                .email("anass@gmail.com")
                .build();
        Employee employee2 = Employee.builder()
                .firstName("Kamal")
                .lastName("el ghazali")
                .email("kamal@gmail.com")
                .cin("z555888")
                .build();
        Employee employee3 = Employee.builder()
                .firstName("kaoutar")
                .lastName("wahbi")
                .cin("g455558")
                .email("kaoutar@gmail.com")
                .build();
        Employee employee4 = Employee.builder()
                .firstName("chaimae")
                .lastName("el hamdouni")
                .cin("cd99887")
                .email("chaimae@gmail.com")
                .build();
        Employee employee5 = Employee.builder()
                .firstName("douae")
                .lastName("lazrak")
                .cin("cd558889")
                .email("douae@gmail.com")
                .build();
        Employee employee6 = Employee.builder()
                .firstName("said")
                .lastName("azzouzi")
                .cin("z778549")
                .email("said@gmail.com")
                .build();
        Employee employee7 = Employee.builder()
                .firstName("hicham")
                .lastName("soussi")
                .cin("z985221")
                .email("hicham@gmail.com")
                .build();

        employeeRepository.saveAll(Arrays.asList(
                employee1,
                employee2,
                employee3,
                employee4,
                employee5,
                employee6,
                employee7
        ));
    }
}
