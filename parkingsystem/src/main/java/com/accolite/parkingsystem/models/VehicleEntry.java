package com.accolite.parkingsystem.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vehicleentry")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VehicleEntry implements Serializable {

	private static final long serialVersionUID = 816436113837233081L;

	private long id;
	private Date inTime;
	private Date outTime;
	private Vehicle vehicle;
	private Gate entryGate;
	private Gate exitGate;
	private Slot slot;

	@Id
	@GeneratedValue(generator = "vehicleentry_generator")
	@SequenceGenerator(name = "vehicleentry_generator", sequenceName = "vehicleentry_sequence", initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "intime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	@Column(name = "outtime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_id", nullable = false)
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gateentry_id", nullable = false)
	public Gate getEntryGate() {
		return entryGate;
	}

	public void setEntryGate(Gate entryGate) {
		this.entryGate = entryGate;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gateexit_id")
	public Gate getExitGate() {
		return exitGate;
	}

	public void setExitGate(Gate exitGate) {
		this.exitGate = exitGate;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "slot_id", nullable = false)
	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof VehicleEntry)) {
			return false;
		}
		VehicleEntry entry = (VehicleEntry) obj;
		if (this.getInTime().equals(entry.getInTime()) && this.getOutTime().equals(entry.getOutTime())) {
			if (this.getEntryGate().equals(entry.getEntryGate()) && this.getExitGate().equals(entry.getExitGate())) {
				if (this.getVehicle().equals(entry.getVehicle()) && this.getSlot().equals(entry.getSlot())) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int hashCode() {
		int hash = 7;
		hash = hash * 31 + (this.getInTime() == null ? 0 : this.getInTime().hashCode());
		hash = hash * 31 + (this.getOutTime() == null ? 0 : this.getOutTime().hashCode());
		hash = hash * 31 + (this.getEntryGate() == null ? 0 : this.getEntryGate().hashCode());
		hash = hash * 31 + (this.getExitGate() == null ? 0 : this.getExitGate().hashCode());
		hash = hash * 31 + (this.getVehicle() == null ? 0 : this.getVehicle().hashCode());
		hash = hash * 31 + (this.getSlot() == null ? 0 : this.getSlot().hashCode());
		return hash;
	}

}
