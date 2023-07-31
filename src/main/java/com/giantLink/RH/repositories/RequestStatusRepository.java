package com.giantLink.RH.repositories;

import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.entities.RequestStatus;
import com.giantLink.RH.enums.State;
import com.giantLink.RH.models.response.RequestStatusResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RequestStatusRepository extends JpaRepository<RequestStatus, Long> {
    @Query(value = "SELECT request_id FROM request_status AS c WHERE c.id = ?1", nativeQuery = true)
    Long findRequestIdById(Long requestId);

    Optional<List<RequestStatus>> findByType(State state);


}
