package com.logistics.tracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.PackageEntity;
import com.logistics.tracking.enums.PackageType;
import com.logistics.tracking.exception.NoRecordAvailableException;
import com.logistics.tracking.repository.PackageEntityRepository;

@Service
public class PackageEntityService {
@Autowired
private PackageEntityRepository packageEntityRepository;
public ResponseStructure<PackageEntity> savePackageEntity(PackageEntity packageEntity){
    PackageEntity newPackageEntity= packageEntityRepository.save(packageEntity);
    ResponseStructure<PackageEntity>  res= new ResponseStructure<>();
    res.setData(newPackageEntity);
    res.setMessage("package entity record inserted successfully");
    res.setStatusCode(HttpStatus.CREATED.value());
    return res;
}
public ResponseStructure<List<PackageEntity>> getPackageEntityByType(PackageType packageType) {
    List<PackageEntity> packageEntities = packageEntityRepository.findByPackageType(packageType);

    if (packageEntities.isEmpty()) {
        throw new NoRecordAvailableException("No packages found for the given package type: " + packageType);
    }

    ResponseStructure<List<PackageEntity>> res = new ResponseStructure<>();
    res.setData(packageEntities);
    res.setMessage("Package records retrieved successfully");
    res.setStatusCode(HttpStatus.OK.value());

    return res;
}
public ResponseStructure<List<PackageEntity>> getAllPackageEntities(){
	 List<PackageEntity> packageEntities = packageEntityRepository.findAll();
	    if (packageEntities.isEmpty()) {
	        throw new NoRecordAvailableException("No packages found for the package entity: ");
	    }

	    ResponseStructure<List<PackageEntity>> res = new ResponseStructure<>();
	    res.setData(packageEntities);
	    res.setMessage("Package records retrieved successfully");
	    res.setStatusCode(HttpStatus.OK.value());

	    return res;
}
}
