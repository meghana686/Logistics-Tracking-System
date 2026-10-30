package com.logistics.tracking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logistics.tracking.enums.TrackingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackingHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String location;
	private String remarks;
    @Enumerated(EnumType.STRING)
	 private TrackingStatus status;
	    @ManyToOne
	    @JoinColumn(name = "shipment_id")
	    @JsonIgnore
	    private Shipment shipment;
}
