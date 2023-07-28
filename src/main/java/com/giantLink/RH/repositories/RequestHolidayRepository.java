package com.giantLink.RH.repositories;

import com.giantLink.RH.entities.RequestHoliday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestHolidayRepository extends JpaRepository<RequestHoliday,Long>{
   List<RequestHoliday> findByEmployeeId(Long employee_id);


}
