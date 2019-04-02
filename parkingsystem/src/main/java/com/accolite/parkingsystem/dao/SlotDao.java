package com.accolite.parkingsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accolite.parkingsystem.models.Slot;

public interface SlotDao extends JpaRepository<Slot, Long> {

}
