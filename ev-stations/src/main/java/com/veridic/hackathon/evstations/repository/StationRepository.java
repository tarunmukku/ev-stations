package com.veridic.hackathon.evstations.repository;

import com.veridic.hackathon.evstations.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  StationRepository extends JpaRepository<Station, Long> {

}
