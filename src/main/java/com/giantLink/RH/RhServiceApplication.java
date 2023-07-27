package com.giantLink.RH;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.HolidayBalance;
import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.entities.RequestStatus;

import com.giantLink.RH.enums.State;
import com.giantLink.RH.exceptions.GLHolidayBalanceIsufficient;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.RequestHolidayRepository;
import com.giantLink.RH.repositories.RequestRepository;
import com.giantLink.RH.repositories.RequestStatusRepository;
import com.giantLink.RH.services.EmployeeService;
import com.giantLink.RH.services.RequestStatusService;
import com.giantLink.RH.services.impl.RequestHolidayServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class RhServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(RhServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(RequestHolidayRepository requestHolidayRepository,
										RequestHolidayServiceImpl requestHolidayService,
										RequestStatusRepository requestStatusRepository,
										EmployeeRepository employeeRepository,
										RequestRepository requestRepository,
										RequestStatusService requestStatusService
	) {
		return args -> {

			HolidayBalance holidayBalance = new HolidayBalance();
			holidayBalance.setBalance(20);
			holidayBalance.setTimestamp(new Date());
			System.out.println("Hicham");
			Employee employee = new Employee();
			employee.setFirstName("Hicham");
			employee.setLastName("ASBIKA");
			employee.setEmail("Hicham.asbika.e@gmail.com");
			employee.setHolidayBalance(holidayBalance);

			employeeRepository.save(employee);

			Calendar startDateCalendar = Calendar.getInstance();
			startDateCalendar.set(Calendar.YEAR, 2023);
			startDateCalendar.set(Calendar.MONTH, Calendar.JULY);
			startDateCalendar.set(Calendar.DAY_OF_MONTH, 26);

			Calendar startDateCalendar2 = Calendar.getInstance();
			startDateCalendar2.set(Calendar.YEAR, 2023);
			startDateCalendar2.set(Calendar.MONTH, Calendar.JULY);
			startDateCalendar2.set(Calendar.DAY_OF_MONTH, 29);


			RequestHoliday requestHoliday = new RequestHoliday();
			requestHoliday.setNumberOfDays(3);
			requestHoliday.setNumberOfPaidLeaves(1);
			requestHoliday.setNumberOfUnpaidLeaves(2);
			requestHoliday.setStartDate(startDateCalendar.getTime());
			requestHoliday.setFinishDate(startDateCalendar2.getTime());
			requestHoliday.setEmployee(employee);
			RequestStatus requestStatus = new RequestStatus();
			requestStatus.setStatusName(State.PENDING);
			requestStatus.setRequest(requestHoliday);
			requestRepository.save(requestHoliday);
			requestStatusRepository.save(requestStatus);
			requestHolidayRepository.save(requestHoliday);

//			try {
//				requestStatusService.processHolidayRequest(3L);
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}

		};
	}


}
