package org.archLog.projetArduino.repositories;

import org.archLog.projetArduino.models.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
    Page<Log> findAll(Pageable pageable);
}
