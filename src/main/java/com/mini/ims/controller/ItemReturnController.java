package com.mini.ims.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mini.ims.Utilities.Helper;
import com.mini.ims.convertor.ItemIssuanceConvertor;
import com.mini.ims.dto.ItemReturnDto;
import com.mini.ims.model.Borrower;
import com.mini.ims.model.Item;
import com.mini.ims.model.Loan;
import com.mini.ims.service.BorrowerService;
import com.mini.ims.service.ItemIssuanceService;
import com.mini.ims.service.ItemService;

@Controller
public class ItemReturnController {

	@Autowired
	private BorrowerService borrowerService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemIssuanceService itemIssuanceService;

	@Autowired
	private ItemIssuanceConvertor itemIssuanceConvertor;

	@GetMapping("/ItemReturnView")
	public String Index(Model model) {
		model.addAttribute("ItemIssuanceDtoList",
				itemIssuanceConvertor.modelToDto(itemIssuanceService.getAllReturnedItem()));
		return "/Item Return/View";
	}

	@GetMapping("/ItemReturnCreate")
	public String Create(Model model) {
		ItemReturnDto itemReturnDto = new ItemReturnDto();
		model.addAttribute("itemReturnDto", itemReturnDto);
		return "/Item Return/Create";
	}

	@PostMapping("/ItemReturnCreate")
	public String Create(@Valid @ModelAttribute("itemReturnDto") ItemReturnDto itemReturnDto, BindingResult result) {
		itemReturnDto.setReturnDate(Helper.getCurrentTime());
		Borrower borrower = null;
		Item item = null;
		Loan loan = null;

		String err = borrowerService.validateBorrowerId(itemReturnDto.getBorrowerId());
		if (!err.isEmpty()) {
			ObjectError error = new ObjectError("globalError", err);
			result.addError(error);
		} else {
			borrower = borrowerService.getBorrowerById(itemReturnDto.getBorrowerId());
		}
		err = itemService.validateItemId(itemReturnDto.getItemId());
		if (!err.isEmpty()) {
			ObjectError error = new ObjectError("globalError", err);
			result.addError(error);
		} else {
			item = itemService.getItemById(itemReturnDto.getItemId());
		}

		try {
			loan = itemIssuanceService.findItemIssued(borrower.getId(), item.getId());
			borrower.removeLoan(loan);
			item.removeLoan(loan);
			loan.setReturnDate();
			loan.calculateFine();
			item.increaseQuantity();
			itemService.saveItem(item);
			itemIssuanceService.saveItemIssued(loan);
		} catch (NullPointerException e) {
			System.out.println("Null Pointer Exception Caught in Item Return Controller.");
			err = "Loan ID does not exist. Invalid input";
			ObjectError error = new ObjectError("globalError", err);
			result.addError(error);
		}
		if (result.hasErrors()) {
			return "/Item Return/Create";
		}
		return "redirect:/ItemReturnView";
	}
}
