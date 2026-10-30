package com.logistics.tracking.enums;

public enum TrackingStatus {

    SHIPMENT_CREATED,
    PICKUP_SCHEDULED,
    PICKED_UP,
    ARRIVED_AT_WAREHOUSE,
    DEPARTED_FROM_WAREHOUSE,
    IN_TRANSIT,
    ARRIVED_AT_DESTINATION,
    OUT_FOR_DELIVERY,
    DELIVERED,
    DELIVERY_FAILED,
    RETURN_INITIATED,
    RETURNED
}