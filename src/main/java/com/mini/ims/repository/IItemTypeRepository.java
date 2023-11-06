package com.mini.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini.ims.model.ItemType;

public interface IItemTypeRepository extends JpaRepository<ItemType, Long> {

}