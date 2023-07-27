package com.giantLink.RH.utilities;

import com.giantLink.RH.repositories.HolidayBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Logger;

@Component
@EnableScheduling
public class ScheduleUtility {
    @Autowired
    HolidayBalanceRepository holidayBalanceRepository;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Scheduled(fixedRate = 5000) // every 24 hours = 86400000 ms
    public void incrementHolidayBalancePerMonth() {
//        holidayBalanceRepository.findAll().forEach(holidayBalance -> {
//            Logger.getLogger("Schedule utility").info("----------------");
//            Logger.getLogger("Schedule utility").info("timestamp : " + formatter.format(holidayBalance.getTimestamp()));
//            Logger.getLogger("Schedule utility").info("now : " + formatter.format(new Date()));
//            Long result;
//            try {
//                result = formatter.parse(formatter.format(new Date())).getTime() - formatter.parse(formatter.format(holidayBalance.getTimestamp())).getTime();
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//            Logger.getLogger("Schedule utility").info("result : " + result.toString());
//            Logger.getLogger("Schedule utility").info("----------------");
//        });
    }

}
