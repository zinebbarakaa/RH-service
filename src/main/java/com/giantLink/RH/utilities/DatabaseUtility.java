package com.giantLink.RH.utilities;

import com.giantLink.RH.entities.*;
import com.giantLink.RH.enums.State;
import com.giantLink.RH.models.request.WarningRequest;
import com.giantLink.RH.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

    @Autowired
    WarningTypeRepository warningTypeRepository;

    @Autowired
    WarningRepository warningRepository;

    public void initDatabase() {
        Logger.getLogger("Database utility").info("Seeding database ...");
        initEmployees();
        initWarningTypes();
        initRequestHoliday();
        Logger.getLogger("Database utility").info("Database seeding complete");
    }

    public void initEmployees() {
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
                .lastName("asbika")
                .cin("z985221")
                .email("hicham.asbika@gmail.com")
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

    public void initWarningTypes() {

        if (warningTypeRepository.count() > 0) return;

        warningTypeRepository.saveAll(Arrays.asList(
                new WarningType("Unjustified Absence", " An unjustified absence warning is given to an employee"
                        + " when they have been absent from work without providing a valid reason or without obtaining proper approval. "),
                new WarningType("Ethical Violation", " If an employee is found to have violated the company's code of ethics or engaged "
                        + "in unethical behavior, they may receive an ethical violation warning "),
                new WarningType("Verbal Warning", " A verbal warning is usually the initial step in addressing a performance or behavioral concern"),
                new WarningType("Written Warning", " If the performance or behavior issue persists after a verbal warning, a written warning may be issued. "),
                new WarningType("Performance Improvement Plan (PIP)", " A Performance Improvement Plan is a structured document that outlines"
                        + " specific performance goals and expectations for an employee over a specified period. "),
                new WarningType("Warning for Absence Without Request", " Warning for Absence Without Request. ")));
    }

    public void initRequestHoliday() {
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.set(Calendar.YEAR, 2023);
        startDateCalendar.set(Calendar.MONTH, Calendar.JULY);
        startDateCalendar.set(Calendar.DAY_OF_MONTH, 26);

        Calendar startDateCalendar2 = Calendar.getInstance();
        startDateCalendar2.set(Calendar.YEAR, 2023);
        startDateCalendar2.set(Calendar.MONTH, Calendar.JULY);
        startDateCalendar2.set(Calendar.DAY_OF_MONTH, 29);


        RequestHoliday requestHoliday = new RequestHoliday();
        requestHoliday.setNumberOfDays(2);
        requestHoliday.setStartDate(startDateCalendar.getTime());
        requestHoliday.setFinishDate(startDateCalendar2.getTime());
        requestHoliday.setEmployee(employeeRepository.findById(7L).get());

        RequestStatus requestStatus = RequestStatus.builder()
                .type(State.PENDING)
                .request(requestHoliday)
                .build();

        requestStatusRepository.save(requestStatus);
        requestHoliday.setStatus(requestStatus);

        requestHolidayRepository.save(requestHoliday);


    }
}
