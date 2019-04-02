package com.accolite.parkingsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accolite.parkingsystem.models.Vehicle;

public interface VehicleDao extends JpaRepository<Vehicle, Long> {

}
