package com.travel.paths.manager.dto;

import lombok.Data;

/**
 * DTO class representing the response for optimal path requests. It contains
 * the status of the response.
 * 
 * @author Marcelo Albarracín
 * @version 1.0
 */
@Data
public class OptimalPathResponse {

	private String status;

	/**
	 * Constructs a new OptimalPathResponse object with the given status.
	 * 
	 * @param status the status of the response
	 */
	public OptimalPathResponse(String status) {
		this.status = status;
	}
}