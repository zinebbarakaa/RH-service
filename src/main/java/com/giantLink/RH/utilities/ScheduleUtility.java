package com.giantLink.RH.utilities;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.RequestAbsence;
import com.giantLink.RH.entities.Warning;
import com.giantLink.RH.entities.WarningType;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.HolidayBalanceRepository;
import com.giantLink.RH.repositories.RequestAbsenceRepository;
import com.giantLink.RH.repositories.WarningRepository;
import com.giantLink.RH.repositories.WarningTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
@EnableScheduling
public class ScheduleUtility {
    @Autowired
    HolidayBalanceRepository holidayBalanceRepository;    
    @Autowired
    private RequestAbsenceRepository requestAbsenceRepository ;
    @Autowired
    private  WarningTypeRepository warningTypeRepository;
    @Autowired
    private  WarningRepository warningRepository;
    


    @Scheduled(fixedRate = 5000) // every 24 hours = 86400000 ms
    public void incrementHolidayBalancePerMonth() {
        holidayBalanceRepository.findAll().forEach(holidayBalance -> {
            long differenceInDays = ChronoUnit.DAYS.between(new Date().toInstant() , holidayBalance.getTimestamp().toInstant());
//            Logger.getLogger("Schedule utility").info(String.valueOf(String.valueOf(differenceInDays)));
            if (differenceInDays <= -30){
                holidayBalance.setBalance(holidayBalance.getBalance() + holidayBalance.getHolidayPerMonth());
                holidayBalance.setTimestamp(new Date());
                holidayBalanceRepository.save(holidayBalance);
            }
        });
    }

    @Scheduled(fixedRate = 40000)
    public void checkJustificationAbsence() {
        requestAbsenceRepository.findAll().forEach(requestAbsence -> {
            long compareDate = new Date().compareTo(requestAbsence.getAbsenceDate());
            if (compareDate == 1 && requestAbsence.isJustification() == false) {
                Warning warning = new Warning();
                warning.setEmployee(requestAbsence.getEmployee());
                warning.setWarningType(warningTypeRepository.findByTitle("Unjustified Absence").get());
                warning.setMessageDetails("Your Absence is not Justified");
                warningRepository.save(warning);
                
            }
        });
    }




}
