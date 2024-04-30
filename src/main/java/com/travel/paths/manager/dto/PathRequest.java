package com.travel.paths.manager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * DTO class representing a request to create a path between two stations. It
 * contains the IDs of the source and destination stations, along with the cost
 * of the path.
 * 
 * @author Marcelo Albarracín
 * @version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PathRequest {

	/**
	 * The ID of the path.
	 */
	private Long id;

	/**
	 * The ID of the source station.
	 */
	@JsonProperty("source_id")
	private Long sourceId;

	/**
	 * The ID of the destination station.
	 */
	@JsonProperty("destination_id")
	private Long destinationId;

	/**
	 * The cost of the path.
	 */
	private double cost;

}