package com.giantLink.RH.repositories;

import com.giantLink.RH.entities.RequestHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RequestHolidayRepository extends JpaRepository<RequestHoliday, Long> {
    @Query(value = "SELECT * FROM request_holiday AS c WHERE c.id = ?1", nativeQuery = true)
    Optional<RequestHoliday> findByRequeStStatusId(Long id);
    List<RequestHoliday> findByEmployeeId(Long employee_id);

}
