package com.accolite.parkingsystem.service;

import java.util.List;
import java.util.Optional;

import com.accolite.parkingsystem.models.Gate;
import com.accolite.parkingsystem.models.ParkingLot;
import com.accolite.parkingsystem.models.Slot;
import com.accolite.parkingsystem.models.Vehicle;
import com.accolite.parkingsystem.models.VehicleEntry;

public interface ParkingSystemService {

	void saveParkingLot(ParkingLot parkingLot);

	Optional<ParkingLot> getParkingLot();

	Optional<Slot> getSlot(Long id);

	Optional<Gate> getGate(Long id);

	void saveVehicleEntryFlow(Vehicle vehicle, Gate gate, Slot slot);

	void saveVehicleExitFlow(Vehicle vehicle, Gate gate, Slot slot, Short amount);
	
	Optional<List<VehicleEntry>> getAllVehicleEntries();
}
