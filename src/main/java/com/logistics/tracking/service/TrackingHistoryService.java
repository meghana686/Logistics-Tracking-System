package com.logistics.tracking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.TrackingHistory;
import com.logistics.tracking.enums.TrackingStatus;
import com.logistics.tracking.exception.IdNotFoundException;
import com.logistics.tracking.exception.NoRecordAvailableException;
import com.logistics.tracking.repository.TrackingHistoryRepository;

@Service
public class TrackingHistoryService {
	@Autowired
	private TrackingHistoryRepository trackingHistoryRepository;
	public ResponseStructure<TrackingHistory> saveTrackingHistory(TrackingHistory trackingHistory){
		TrackingHistory newTrackingHistory=trackingHistoryRepository.save(trackingHistory);
		ResponseStructure<TrackingHistory> res= new ResponseStructure<>();
		res.setData(newTrackingHistory);
		res.setMessage("tracking history record inserted successfully");
		res.setStatusCode(HttpStatus.CREATED.value());
		return res;
}
	public ResponseStructure<List<TrackingHistory>> getAllTrackingHistories(){
		List<TrackingHistory> trackingHistory=trackingHistoryRepository.findAll();
		if(trackingHistory.isEmpty()) {
			throw new NoRecordAvailableException("no records available in the tracking history");
		}
		ResponseStructure<List<TrackingHistory>> res= new ResponseStructure<>();
		res.setData(trackingHistory);
		res.setMessage("tracking history records retrieved successfully");
		res.setStatusCode(HttpStatus.OK.value());
		return res;
	}
	public ResponseStructure<TrackingHistory> getTrackingHistoryById(Integer id){
		Optional<TrackingHistory> trackingHistory=trackingHistoryRepository.findById(id);
		if(trackingHistory.isEmpty()) {
			throw new IdNotFoundException("Tracking history not found with given id :"+id);
		}
		ResponseStructure<TrackingHistory> res= new ResponseStructure<>();
		res.setData(trackingHistory.get());
		res.setMessage("tracking history record retrived successfully");
		res.setStatusCode(HttpStatus.OK.value());
		return res;	
	}
	public ResponseStructure<List<TrackingHistory>> getTrackingHistoryByTrackingNumber(String trackingNumber){
		List<TrackingHistory> trackingHistory=trackingHistoryRepository.findByShipment_TrackingNumber(trackingNumber);
		if(trackingHistory.isEmpty()) {
			throw new NoRecordAvailableException("no records available in the tracking history");
		}
		ResponseStructure<List<TrackingHistory>> res= new ResponseStructure<>();
		res.setData(trackingHistory);
		res.setMessage("tracking history records retrieved successfully");
		res.setStatusCode(HttpStatus.OK.value());
		return res;
	}

	public ResponseStructure<List<TrackingHistory>> getTrackingHistoryByStatus(TrackingStatus status){
		List<TrackingHistory> trackingHistory=trackingHistoryRepository.findByStatus(status);
		if(trackingHistory.isEmpty()) {
			throw new NoRecordAvailableException("no records available in the tracking history");
		}
		ResponseStructure<List<TrackingHistory>> res= new ResponseStructure<>();
		res.setData(trackingHistory);
		res.setMessage("tracking history records retrieved successfully");
		res.setStatusCode(HttpStatus.OK.value());
		return res;
	}
	public ResponseStructure<List<TrackingHistory>> getAllTrackingHistoryOfShipments(Integer id) {

	    List<TrackingHistory> trackingHistory =
	            trackingHistoryRepository.findByShipment_Id(id);

	    if (trackingHistory.isEmpty()) {
	        throw new NoRecordAvailableException(
	                "No records available in the tracking history");
	    }

	    ResponseStructure<List<TrackingHistory>> res = new ResponseStructure<>();

	    res.setData(trackingHistory);
	    res.setMessage("Tracking history records retrieved successfully");
	    res.setStatusCode(HttpStatus.OK.value());

	    return res;
	}
	


	
}