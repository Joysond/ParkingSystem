package com.accolite.parkingsystem.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accolite.parkingsystem.dao.GateDao;
import com.accolite.parkingsystem.dao.ParkingLotDao;
import com.accolite.parkingsystem.dao.SlotDao;
import com.accolite.parkingsystem.dao.VehicleDao;
import com.accolite.parkingsystem.dao.VehicleEntryDao;
import com.accolite.parkingsystem.dao.VehicleInfoDao;
import com.accolite.parkingsystem.models.Gate;
import com.accolite.parkingsystem.models.ParkingLot;
import com.accolite.parkingsystem.models.Slot;
import com.accolite.parkingsystem.models.Vehicle;
import com.accolite.parkingsystem.models.VehicleEntry;
import com.accolite.parkingsystem.models.VehicleInfo;
import com.accolite.parkingsystem.utils.ParkingSystemException;

@Service
@Transactional
public class ParkingSystemServiceImpl implements ParkingSystemService {

	@Autowired
	ParkingLotDao parkingLotDao;

	@Autowired
	SlotDao slotDao;

	@Autowired
	GateDao gateDao;

	@Autowired
	VehicleDao vehicleDao;

	@Autowired
	VehicleInfoDao vehicleInfoDao;

	@Autowired
	VehicleEntryDao vehicleEntryDao;

	@Override
	public void saveParkingLot(ParkingLot parkingLot) {
		this.parkingLotDao.save(parkingLot);
	}

	@Override
	public Optional<ParkingLot> getParkingLot() {
		return Optional.ofNullable(this.parkingLotDao.findAll().get(0));
	}

	@Override
	public Optional<Slot> getSlot(Long id) {
		return this.slotDao.findById(id);
	}

	@Override
	public Optional<Gate> getGate(Long id) {
		return this.gateDao.findById(id);
	}

	@Override
	public void saveVehicleEntryFlow(Vehicle vehicle, Gate gate, Slot slot) {
		if (vehicle.getNumber().isEmpty()) {
			throw new ParkingSystemException("Vehicle number can not be empty !");
		}
		if (vehicle.getType() == null) {
			throw new ParkingSystemException("Vehicle type can not be null !");
		}

		VehicleInfo vehicleInfo = new VehicleInfo();
		vehicleInfo.setInTime(new Date(System.currentTimeMillis()));
		vehicleInfo.setEntryGate(gate);
		vehicle.setInfo(vehicleInfo);
		saveVehicle(vehicle);

		// Update slot entry
		slot.setIsFree(false);
		slot.setVehicle(vehicle);
		this.slotDao.save(slot);
	}

	@Override
	public void saveVehicleExitFlow(Vehicle vehicle, Gate exitGate, Slot slot, Short amount) {
		Optional<Slot> optSlot = this.slotDao.findById(slot.getId());
		Optional<Vehicle> optVehicle = this.vehicleDao.findById(vehicle.getId());
		if (optVehicle.isPresent()) {
			Vehicle savedVehicle = optVehicle.get();

			// Updating vehicle info
			VehicleInfo vehicleInfo = savedVehicle.getInfo();
			vehicleInfo.setOutTime(new Date(System.currentTimeMillis()));
			vehicleInfo.setExitGate(exitGate);
			vehicleInfo.setAmount(amount);
			this.vehicleInfoDao.save(vehicleInfo);

			// Making an entry in Vehicle Entry table. We are creating it only when vehicle
			// exits the parking lot
			VehicleEntry entry = new VehicleEntry();
			entry.setEntryGate(vehicleInfo.getEntryGate());
			entry.setExitGate(exitGate);
			entry.setInTime(vehicleInfo.getInTime());
			entry.setOutTime(vehicleInfo.getOutTime());
			entry.setSlot(slot);
			entry.setVehicle(savedVehicle);
			this.vehicleEntryDao.save(entry);

			// Update slot entry and setting vehicle to null
			Slot savedSlot = optSlot.get();
			savedSlot.setIsFree(true);
			savedSlot.setVehicle(null);
			this.slotDao.save(savedSlot);
		} else {
			throw new ParkingSystemException("Vehicle entry does not exist in system !");
		}
	}

	@Override
	public Optional<List<VehicleEntry>> getAllVehicleEntries() {
		return Optional.ofNullable(this.vehicleEntryDao.findAll());
	}

	private void saveVehicle(Vehicle vehicle) {
		this.vehicleDao.save(vehicle);
	}

}
