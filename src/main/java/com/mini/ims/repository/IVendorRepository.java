package com.mini.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.ims.model.Vendor;

@Repository
public interface IVendorRepository extends JpaRepository<Vendor, Long> {

}
