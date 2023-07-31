package com.giantLink.RH.repositories;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.HolidayBalance;
import com.giantLink.RH.entities.RequestHoliday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayBalanceRepository extends JpaRepository<HolidayBalance, Long>
{
}
