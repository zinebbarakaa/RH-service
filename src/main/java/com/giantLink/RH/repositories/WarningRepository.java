package com.giantLink.RH.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.Warning;
import com.giantLink.RH.entities.WarningType;

public interface WarningRepository extends JpaRepository<Warning, Long> {

	public List<Warning>  findByEmployee(Employee employee);
	public List<Warning>  findByWarningType(WarningType warningType);
	public boolean existsByEmployeeAndWarningType_Title(Employee employee, String string);
}
