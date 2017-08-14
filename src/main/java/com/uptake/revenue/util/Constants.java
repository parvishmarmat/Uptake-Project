package com.uptake.revenue.util;
/**
 * 
 * @author Parvish Marmat
 *
 */
public interface Constants {
	static final String BAD_ARGUMENT_MESSAGE = "At least one expected argument is not provided as part of the request. Please re-submit with all required parameters.";
	static final String BAD_ARGUMENT_CODE = "Invalid argument";
	static final String BAD_ARGUMENT_TITLE = "Required argument is not found.";
	static final String RESOURCE_NOT_FOUND_CODE = "Requested resource is not found";
	static final String RESOURCE_NOT_FOUND_TITLE = "No resource is found for given inputs.";
	static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource not found for the input provided. It is unusual to not find resource for this request.";
	static final String GENERIC_RUNTIME_ERROR_CODE = "Runtime error.";
	static final String GENERIC_RUNTIME_ERROR_TITLE = "Unexpected error occured while processing request.";
	static final String GENERIC_RUNTIME_ERROR_MESSAEGE = "Internal server error, caused most probably due to inconsistency in data.";
	static final int DEFAULT_PAGE_SIZE = 10;
	static final String USER_NOT_EXIST = "User does not exist";
	static final String WRONG_PASSWORD = "Password is wrong";
	static final String DATA_NOT_EXIST = "Data is not available for the user";
	
	static final String REVENUE_COLLECTION = "revenue";
	static final String USER_COLLECTION = "user";
	
	static final String TYPE_SALES = "Sales";
	static final String TYPE_RENTAL = "Rental";
	static final String TYPE_LABOR = "Labor";
	static final String TYPE_PARTS = "Parts";
}
