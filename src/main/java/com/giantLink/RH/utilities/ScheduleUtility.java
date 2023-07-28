package com.giantLink.RH.utilities;

import com.giantLink.RH.repositories.HolidayBalanceRepository;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
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
@EnableAsync
public class ScheduleUtility {
    @Autowired
    HolidayBalanceRepository holidayBalanceRepository;

    @Async
    @Scheduled(fixedRate = 43200000) // every 24 hours = 86400000 ms | 12 hours = 43200000
    public void incrementHolidayBalancePerMonth() {
        holidayBalanceRepository.findAll().forEach(holidayBalance -> {
            long differenceInDays = ChronoUnit.DAYS.between(new Date().toInstant() , holidayBalance.getTimestamp().toInstant());
            if (differenceInDays <= -30){
                holidayBalance.setBalance(holidayBalance.getBalance() + holidayBalance.getHolidayPerMonth());
                holidayBalance.setTimestamp(new Date());
                holidayBalanceRepository.save(holidayBalance);
            }
        });
    }
}
