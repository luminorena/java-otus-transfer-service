package ru.otus.java.pro.spring.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.java.pro.spring.app.entities.Transfer;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransfersRepository extends JpaRepository<Transfer, String> {
    Optional<Transfer> findByIdAndClientId(String id, String clientId);

    @Query("SELECT t FROM Transfer t WHERE t.clientId = :clientId OR t.targetClientId = :clientId")
    List<Transfer> findAllByClientIdOrTargetClientId(@Param("clientId") String clientId);

}
