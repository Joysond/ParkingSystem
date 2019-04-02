package com.accolite.parkingsystem.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "gate")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Gate implements Serializable {

	private static final long serialVersionUID = -5177827408867142394L;

	private Long id;
	private Short number;

	@Id
	@GeneratedValue(generator = "gate_generator")
	@SequenceGenerator(name = "gate_generator", sequenceName = "gate_sequence", initialValue = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "number")
	public Short getNumber() {
		return number;
	}

	public void setNumber(Short number) {
		this.number = number;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (!(obj instanceof Gate)) {
			return false;
		}
		Gate gate = (Gate) obj;
		if (this.getNumber().equals(gate.getNumber())) {
			return true;
		} else {
			return false;
		}
	}

	public int hashCode() {
		return this.getNumber().hashCode();
	}

}
