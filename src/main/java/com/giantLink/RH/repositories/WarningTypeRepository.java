package com.giantLink.RH.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giantLink.RH.entities.WarningType;

public interface WarningTypeRepository extends JpaRepository<WarningType, Long> {

	Optional<WarningType> findByTitle(String title);
}
