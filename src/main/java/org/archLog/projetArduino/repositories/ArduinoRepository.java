package org.archLog.projetArduino.repositories;

import org.archLog.projetArduino.models.Arduino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ArduinoRepository extends JpaRepository<Arduino, Integer> {
}
