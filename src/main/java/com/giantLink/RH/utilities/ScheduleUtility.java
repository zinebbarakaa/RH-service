package com.giantLink.RH.utilities;

import com.giantLink.RH.repositories.HolidayBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Component
@EnableScheduling
public class ScheduleUtility {
    @Autowired
    HolidayBalanceRepository holidayBalanceRepository;
    @Scheduled(fixedRate = 86400000) // every 24 hours = 86400000 ms
    public void incrementHolidayBalancePerMonth(){
//        holidayBalanceRepository.findAll().forEach(holidayBalance -> {
//            int result = holidayBalance.getTimestamp().compareTo(new Date());
//            Logger.getLogger("Schedule utility").info("----------------");
//            Logger.getLogger("Schedule utility").info("timestamp : " + holidayBalance.getTimestamp().toString());
//            Logger.getLogger("Schedule utility").info("current time : " + new Date());
//            Logger.getLogger("Schedule utility").info("result : " + String.valueOf(result));
//            Logger.getLogger("Schedule utility").info("----------------");
//        });
    }

}
