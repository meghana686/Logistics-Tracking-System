package com.logistics.tracking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logistics.tracking.enums.PackageType;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private PackageType packageType;

    private Boolean fragile;

    private String dimensions;

    @OneToOne
    @JoinColumn(name = "shipment_id")
    @JsonIgnore
    private Shipment shipment;
}
