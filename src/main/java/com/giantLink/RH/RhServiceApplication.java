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
}
