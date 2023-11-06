package com.mini.ims.service;

import com.mini.ims.model.Vendor;

public interface IVendorService {
	Vendor getVendorById(long id);

	Vendor getVendorByName(String name);

	String validateVendorId(long id);
	
	String validateVendorName(String vendorName);
}
