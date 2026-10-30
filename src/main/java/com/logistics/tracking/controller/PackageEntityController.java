package com.logistics.tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.PackageEntity;
import com.logistics.tracking.enums.PackageType;
import com.logistics.tracking.service.PackageEntityService;

@RestController
public class PackageEntityController {
@Autowired
private PackageEntityService packageEntityService;
@PostMapping("/package-entity")
public ResponseEntity<ResponseStructure<PackageEntity>> savePackageEntity(@RequestBody  PackageEntity packageEntity){
	ResponseStructure<PackageEntity> res =packageEntityService.savePackageEntity(packageEntity);
	return new ResponseEntity<>(res,HttpStatus.CREATED);
}
@GetMapping("/package-entity/{packageType}")
public ResponseEntity<ResponseStructure<List<PackageEntity>>> getPackageEntityByPackageType(
        @PathVariable PackageType packageType) {

    ResponseStructure<List<PackageEntity>> res = packageEntityService.getPackageEntityByType(packageType);
    return new ResponseEntity<>(res, HttpStatus.OK);
}
@GetMapping("/package-entity")
public ResponseEntity<ResponseStructure<List<PackageEntity>>> getAllPackageEntitys() {

    ResponseStructure<List<PackageEntity>> res = packageEntityService.getAllPackageEntities();
    return new ResponseEntity<>(res, HttpStatus.OK);
}
}
