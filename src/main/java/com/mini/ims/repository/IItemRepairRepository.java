package com.mini.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.ims.model.ItemRepair;

@Repository
public interface IItemRepairRepository extends JpaRepository<ItemRepair, Long> {

}
