package com.accolite.parkingsystem.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.parkingsystem.models.Gate;
import com.accolite.parkingsystem.models.ParkingLot;
import com.accolite.parkingsystem.models.Slot;
import com.accolite.parkingsystem.models.Vehicle;
import com.accolite.parkingsystem.models.VehicleEntry;
import com.accolite.parkingsystem.service.ParkingSystemService;
import com.accolite.parkingsystem.utils.ParkingSystemResponse;

@RequestMapping(path = "parking-system")
@RestController
public class ParkingSystemControllers {

	@Autowired
	ParkingSystemService parkingLotService;

	@GetMapping("/version")
	public String version() {
		return "Parking System : 1.0";
	}

	@PostMapping("/parking-lot")
	public ResponseEntity<ParkingSystemResponse> createParkingLot(@RequestBody ParkingLot parkingLot) {
		this.parkingLotService.saveParkingLot(parkingLot);
		return new ResponseEntity<ParkingSystemResponse>(new ParkingSystemResponse("Parking Lot Created"),
				HttpStatus.CREATED);
	}

	@GetMapping("/parking-lot")
	public ResponseEntity<ParkingLot> getParkingLot() {
		return new ResponseEntity<ParkingLot>(this.parkingLotService.getParkingLot().get(), HttpStatus.OK);
	}

	@PostMapping("/vehicle-entry-flow/{gateId}/{slotId}")
	public ResponseEntity<ParkingSystemResponse> createVehicleEntryFlow(@RequestBody Vehicle vehicle,
			@PathVariable Long gateId, @PathVariable Long slotId) {
		Optional<Slot> optSlot = this.parkingLotService.getSlot(slotId);
		ParkingSystemResponse psRes = null;
		HttpStatus status = null;
		if (optSlot.isPresent()) {
			if (optSlot.get().getIsFree()) {
				Optional<Gate> optGate = this.parkingLotService.getGate(gateId);
				if (optGate.isPresent()) {
					this.parkingLotService.saveVehicleEntryFlow(vehicle, optGate.get(), optSlot.get());
					psRes = new ParkingSystemResponse("Vehicle entry created");
					status = HttpStatus.CREATED;
				} else {
					psRes = new ParkingSystemResponse("Invalid gate, Could not locate entry gate");
					status = HttpStatus.NOT_FOUND;
				}
			} else {
				psRes = new ParkingSystemResponse("Parking Slot is not free");
				status = HttpStatus.CONFLICT;
			}
		} else {
			psRes = new ParkingSystemResponse("Invalid slot, Could not locate slot for Vehicle");
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<ParkingSystemResponse>(psRes, status);
	}

	@PostMapping("/vehicle-exit-flow/{gateId}/{slotId}/{amount}")
	public ResponseEntity<ParkingSystemResponse> createVehicleExitFlow(@RequestBody Vehicle vehicle,
			@PathVariable Long gateId, @PathVariable Long slotId, @PathVariable Short amount) {
		Optional<Slot> optSlot = this.parkingLotService.getSlot(slotId);
		ParkingSystemResponse psRes = null;
		HttpStatus status = null;
		if (optSlot.isPresent()) {
			Optional<Gate> optGate = this.parkingLotService.getGate(gateId);
			if (optGate.isPresent()) {
				this.parkingLotService.saveVehicleExitFlow(vehicle, optGate.get(), optSlot.get(), amount);
				psRes = new ParkingSystemResponse("Vehicle exit flow created");
				status = HttpStatus.CREATED;
			} else {
				psRes = new ParkingSystemResponse("Invalid gate, Could not locate entry gate");
				status = HttpStatus.NOT_FOUND;
			}
		} else {
			psRes = new ParkingSystemResponse("Invalid slot, Could not locate slot for Vehicle");
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<ParkingSystemResponse>(psRes, status);
	}

	@GetMapping("/vehicle-entries")
	public ResponseEntity<List<VehicleEntry>> getAllVehicleEntries() {
		Optional<List<VehicleEntry>> optListEntries = this.parkingLotService.getAllVehicleEntries();
		if (optListEntries.isPresent()) {
			return new ResponseEntity<List<VehicleEntry>>(optListEntries.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<VehicleEntry>>(new ArrayList<VehicleEntry>(), HttpStatus.NO_CONTENT);
		}
	}

}
