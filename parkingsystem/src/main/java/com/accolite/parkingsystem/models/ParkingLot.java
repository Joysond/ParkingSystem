package com.accolite.parkingsystem.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "parkinglot")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ParkingLot implements Serializable {

	private static final long serialVersionUID = 2723711740460904592L;

	private Long id;
	private Set<Section> sections;
	private Set<Gate> gates;

	@Id
	@GeneratedValue(generator = "parkinglot_generator")
	@SequenceGenerator(name = "parkinglot_generator", sequenceName = "parkinglot_sequence", initialValue = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "section_id", nullable = false)
	public Set<Section> getSections() {
		return sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "gate_id", nullable = false)
	public Set<Gate> getGates() {
		return gates;
	}

	public void setGates(Set<Gate> gates) {
		this.gates = gates;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ParkingLot)) {
			return false;
		}
		ParkingLot lot = (ParkingLot) obj;
		if (this.getGates().equals(lot.getGates())) {
			if (this.getSections().equals(lot.getSections())) {
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
		hash = 31 * hash + (this.getGates() == null ? 0 : this.getGates().hashCode());
		hash = 31 * hash + (this.getSections() == null ? 0 : this.getGates().hashCode());
		return hash;
	}
}
