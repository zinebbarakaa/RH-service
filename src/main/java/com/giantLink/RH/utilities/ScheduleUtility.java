package com.giantLink.RH.utilities;

import com.giantLink.RH.repositories.HolidayBalanceRepository;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Component
@EnableScheduling
public class ScheduleUtility {
    @Autowired
    HolidayBalanceRepository holidayBalanceRepository;

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

}
