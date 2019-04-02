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
@Table(name = "vehicleinfo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VehicleInfo implements Serializable {

	private static final long serialVersionUID = 4866416163867005563L;

	private Long id;
	private Date inTime;
	private Date outTime;
	private Short amount;
	private Gate entryGate;
	private Gate exitGate;

	@Id
	@GeneratedValue(generator = "vehicleinfo_generator")
	@SequenceGenerator(name = "vehicleinfo_generator", sequenceName = "vehicleinfo_sequence", initialValue = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Column(name = "outtime")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	@Column(name = "amount")
	public Short getAmount() {
		return amount;
	}

	public void setAmount(Short amount) {
		this.amount = amount;
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

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof VehicleInfo)) {
			return false;
		}
		VehicleInfo info = (VehicleInfo) obj;
		if (this.getInTime().equals(info.getInTime()) && this.getOutTime().equals(info.getOutTime())) {
			if (this.getEntryGate().equals(info.getEntryGate()) && this.getExitGate().equals(info.getExitGate())) {
				if (this.getAmount().equals(info.getAmount())) {
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
		hash = 31 * hash + (this.getInTime() == null ? 0 : this.getInTime().hashCode());
		hash = 31 * hash + (this.getOutTime() == null ? 0 : this.getOutTime().hashCode());
		hash = 31 * hash + (this.getAmount() == null ? 0 : this.getAmount().hashCode());
		hash = 31 * hash + (this.getEntryGate() == null ? 0 : this.getEntryGate().hashCode());
		hash = 31 * hash + (this.getExitGate() == null ? 0 : this.getExitGate().hashCode());
		return hash;
	}
}
