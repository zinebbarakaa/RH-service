package com.giantLink.RH.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giantLink.RH.entities.RequestAbsence;

public interface RequestAbsenceRepository extends JpaRepository<RequestAbsence, Long> {

	List<RequestAbsence> findByAbsenceDateBetween(LocalDate oneMonthAgo, LocalDate currentDate);
	
	

}
