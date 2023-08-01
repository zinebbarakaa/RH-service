package com.giantLink.RH.repositories;

import com.giantLink.RH.entities.Holiday;
import com.giantLink.RH.entities.PredefinedHoliday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredefinedHolidayRepository extends JpaRepository<PredefinedHoliday,Long> {

}
