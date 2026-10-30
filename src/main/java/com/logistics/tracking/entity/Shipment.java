package com.logistics.tracking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logistics.tracking.enums.ShipmentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String trackingNumber;

    private String source;

    private String destination;

    private Double weight;

    @CreationTimestamp
    private LocalDateTime shipmentDateTime;

    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "delivery_agent_id")
    private DeliveryAgent deliveryAgent;

  
    @OneToOne(mappedBy = "shipment")
    @JsonIgnore
    private Warehouse warehouse;


    @OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL)
    @JsonIgnore
    private PackageEntity packageEntity;

    
    @OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL)
    @JsonIgnore
    private Payment payment;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TrackingHistory> trackingHistories;
}