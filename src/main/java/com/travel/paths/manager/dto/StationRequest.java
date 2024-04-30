package com.travel.paths.manager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * DTO class representing a request to create a new station. It contains the
 * name of the station.
 * 
 * @author Marcelo Albarracín
 * @version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationRequest {

	/**
	 * The name of the station.
	 */
	private String name;

}