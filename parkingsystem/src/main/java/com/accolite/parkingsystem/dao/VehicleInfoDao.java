package com.accolite.parkingsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accolite.parkingsystem.models.VehicleInfo;

public interface VehicleInfoDao extends JpaRepository<VehicleInfo, Long> {

}
