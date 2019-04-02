package com.accolite.parkingsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accolite.parkingsystem.models.VehicleEntry;

public interface VehicleEntryDao extends JpaRepository<VehicleEntry, Long> {

}
