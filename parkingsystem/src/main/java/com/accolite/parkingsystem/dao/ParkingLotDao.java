package com.accolite.parkingsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accolite.parkingsystem.models.ParkingLot;

public interface ParkingLotDao extends JpaRepository<ParkingLot, Long> {

}