package com.logistics.tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.PackageEntity;
import com.logistics.tracking.enums.PackageType;

public interface PackageEntityRepository  extends JpaRepository<PackageEntity, Integer> {
	List<PackageEntity> findByPackageType(PackageType packageType);
}
