package com.giantLink.RH.repositories;

import com.giantLink.RH.entities.RequestHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RequestHolidayRepository extends JpaRepository<RequestHoliday,Long> {

}
