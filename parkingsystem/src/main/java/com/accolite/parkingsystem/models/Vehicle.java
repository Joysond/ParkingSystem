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
@Table(name = "vehicle")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 5690908711852625122L;

	private Long id;
	private String number;
	private VehicleType type;
	private VehicleInfo info;

	@Id
	@GeneratedValue(generator = "vehicle_generator")
	@SequenceGenerator(name = "vehicle_generator", sequenceName = "vehicle_sequence", initialValue = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "number")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "type")
	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "vehicleinfo_id", nullable = false)
	public VehicleInfo getInfo() {
		return info;
	}

	public void setInfo(VehicleInfo info) {
		this.info = info;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Vehicle)) {
			return false;
		}
		Vehicle vehicle = (Vehicle) obj;
		if (this.getNumber().equals(vehicle.getNumber())) {
			if (this.getType() == vehicle.getType()) {
				if (this.getInfo() == null && vehicle.getInfo() != null) {
					return false;
				} else if (this.getInfo() != null && vehicle.getInfo() == null) {
					return false;
				} else if (this.getInfo() == null && vehicle.getInfo() == null) {
					return true;
				} else if (this.getInfo().equals(vehicle.getInfo())) {
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
		hash = 31 * hash + (this.getNumber() == null ? 0 : this.getNumber().hashCode());
		hash = 31 * hash + (this.getType() == null ? 0 : this.getType().hashCode());
		hash = 31 * hash + (this.getInfo() == null ? 0 : this.getInfo().hashCode());
		return hash;
	}
}
