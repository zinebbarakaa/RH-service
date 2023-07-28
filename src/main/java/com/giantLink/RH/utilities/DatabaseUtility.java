package com.giantLink.RH.utilities;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.HolidayBalance;
import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.entities.RequestStatus;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.HolidayBalanceRepository;
import com.giantLink.RH.repositories.RequestHolidayRepository;
import com.giantLink.RH.repositories.RequestStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class DatabaseUtility {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    HolidayBalanceRepository holidayBalanceRepository;
    @Autowired
    RequestStatusRepository requestStatusRepository;
    @Autowired
    RequestHolidayRepository requestHolidayRepository;


    public void initDatabase(){
        Logger.getLogger("Database utility").info("Seeding database ...");
        iniRequestStatus();
        initEmployees();
        initHolidayBalance();
        initHolidayRequest();
        Logger.getLogger("Database utility").info("Database seeding complete");
    }

    public void iniRequestStatus(){
        if (requestStatusRepository.count() > 0) return;
        RequestStatus requestStatus1 = RequestStatus.builder().type("pending").build();
        RequestStatus requestStatus2 = RequestStatus.builder().type("approved").build();
        RequestStatus requestStatus3 = RequestStatus.builder().type("denied").build();
        requestStatusRepository.saveAll(Arrays.asList(
                requestStatus1,
                requestStatus2,
                requestStatus3
        ));

    }
    public void initHolidayBalance(){
        if (holidayBalanceRepository.count() > 0) return;
        HolidayBalance holidayBalance1 = HolidayBalance.builder()
                .balance(4)
                .timestamp(new Date())
                .balance(5)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        HolidayBalance holidayBalance2 = HolidayBalance.builder()
                .balance(4)
                .timestamp(new Date())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        HolidayBalance holidayBalance3 = HolidayBalance.builder()
                .balance(6)
                .timestamp(new Date())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        holidayBalanceRepository.saveAll(Arrays.asList(
                holidayBalance1,
                holidayBalance2,
                holidayBalance3

        ));
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

        HolidayBalance holidayBalance1 = new HolidayBalance();
        HolidayBalance holidayBalance2 = new HolidayBalance();
        HolidayBalance holidayBalance3 = new HolidayBalance();
        HolidayBalance holidayBalance4 = new HolidayBalance();
        HolidayBalance holidayBalance5 = new HolidayBalance();
        HolidayBalance holidayBalance6 = new HolidayBalance();
        HolidayBalance holidayBalance7 = new HolidayBalance();

        holidayBalanceRepository.saveAll(Arrays.asList(
                holidayBalance1,
                holidayBalance2,
                holidayBalance3,
                holidayBalance4,
                holidayBalance5,
                holidayBalance6,
                holidayBalance7
        ));

        employee1.setHolidayBalance(holidayBalance1);
        employee2.setHolidayBalance(holidayBalance2);
        employee3.setHolidayBalance(holidayBalance3);
        employee4.setHolidayBalance(holidayBalance4);
        employee5.setHolidayBalance(holidayBalance5);
        employee6.setHolidayBalance(holidayBalance6);
        employee7.setHolidayBalance(holidayBalance7);

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
    public void initHolidayRequest(){
        if (requestHolidayRepository.count() > 0)return;
        Employee employee1 = employeeRepository.findById(1L).get();
        Employee employee2 = employeeRepository.findById(5L).get();
        Employee employee3 = employeeRepository.findById(3L).get();

        RequestStatus requestStatus1 = requestStatusRepository.findById(1L).get();
        RequestStatus requestStatus2 = requestStatusRepository.findById(2L).get();
        RequestStatus requestStatus3 = requestStatusRepository.findById(3L).get();

        RequestHoliday requestHoliday1 = RequestHoliday.builder()
                .numberOfDays(3L)
                .startDate(new Date())
                .returnDate(new Date())
                .finishDate(new Date())
                .numberOfPaidLeaves(0L)
                .numberOfUnpaidLeaves(1L)
                .build();
        RequestHoliday requestHoliday2 = RequestHoliday.builder()
                .numberOfDays(3L)
                .startDate(new Date())
                .returnDate(new Date())
                .finishDate(new Date())
                .numberOfPaidLeaves(0L)
                .numberOfUnpaidLeaves(1L)
                .build();
        RequestHoliday requestHoliday3 = RequestHoliday.builder()
                .numberOfDays(3L)
                .startDate(new Date())
                .returnDate(new Date())
                .finishDate(new Date())
                .numberOfPaidLeaves(0L)
                .numberOfUnpaidLeaves(1L)
                .build();

        requestHoliday1.setEmployee(employee3);
        requestHoliday2.setEmployee(employee1);
        requestHoliday3.setEmployee(employee2);

        requestHoliday1.setStatus(requestStatus1);
        requestHoliday2.setStatus(requestStatus2);
        requestHoliday3.setStatus(requestStatus3);

        requestHolidayRepository.saveAll(Arrays.asList(
                requestHoliday1,
                requestHoliday2,
                requestHoliday3
        ));
    }
}
