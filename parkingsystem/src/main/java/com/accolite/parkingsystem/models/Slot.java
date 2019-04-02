package com.accolite.parkingsystem.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "slot")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Slot implements Serializable {

	private static final long serialVersionUID = -6164452892793977758L;

	private Long id;
	private Boolean isFree;
	private Vehicle vehicle;

	@Id
	@GeneratedValue(generator = "slot_generator")
	@SequenceGenerator(name = "slot_generator", sequenceName = "slot_sequence", initialValue = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "isfree")
	public Boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "vehicle_id")
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (!(obj instanceof Slot)) {
			return false;
		}
		Slot slot = (Slot) obj;
		if (this.getIsFree().equals(slot.getIsFree())) {
			if (this.getVehicle() == null && slot.getVehicle() == null) {
				return false;
			} else if (this.getVehicle() == null && slot.getVehicle() != null) {
				return false;
			} else if (this.getVehicle() != null && slot.getVehicle() == null) {
				return false;
			} else if (this.getVehicle().equals(slot.getVehicle())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + (this.getIsFree() == null ? 0 : this.getIsFree().hashCode());
		hash = 31 * hash + (this.getVehicle() == null ? 0 : this.getVehicle().hashCode());
		return hash;
	}

}
