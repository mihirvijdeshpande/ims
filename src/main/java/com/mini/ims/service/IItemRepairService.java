package com.mini.ims.service;

import java.util.List;

import com.mini.ims.model.ItemRepair;

public interface IItemRepairService {
	List<ItemRepair> getAllRepairItems();

	void saveItemRepair(ItemRepair itemRepair);

	void deleteItemRepairById(long id);

	ItemRepair findItemRepairById(long id);
}