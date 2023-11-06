package com.mini.ims.service;

import java.util.List;

import com.mini.ims.model.Item;

public interface IItemService {
	Item getItemById(long id);

	String validateItemId(long id);

	long findItemIdByLoanId(long loanId);

	List<Item> getAllItems();

	void saveItem(Item item);

	String validateItemId(String itemName, String itemType);

	void deleteItem(long itemId);
}
