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
@Table(name = "section")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Section implements Serializable {

	private static final long serialVersionUID = 4077739425739038024L;

	private Long id;
	private Short number;
	private Set<Slot> slots;

	@Id
	@GeneratedValue(generator = "section_generator")
	@SequenceGenerator(name = "section_generator", sequenceName = "section_sequence", initialValue = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getNumber() {
		return number;
	}

	public void setNumber(Short number) {
		this.number = number;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "slot_id", nullable = false)
	public Set<Slot> getSlots() {
		return slots;
	}

	public void setSlots(Set<Slot> slots) {
		this.slots = slots;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (!(obj instanceof Section)) {
			return false;
		}
		Section section = (Section) obj;
		if (this.getNumber().equals(section.getNumber())) {
			if (this.getSlots() == null && section.getSlots() == null) {
				return true;
			} else if (this.getSlots() == null && section.getSlots() != null) {
				return false;
			} else if (this.getSlots() != null && section.getSlots() == null) {
				return false;
			} else if (this.getSlots().equals(section.getSlots())) {
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
		hash = 31 * hash + (this.getNumber() == null ? 0 : this.getNumber().hashCode());
		hash = 31 * hash + (this.getSlots() == null ? 0 : this.getSlots().hashCode());
		return hash;
	}
}
