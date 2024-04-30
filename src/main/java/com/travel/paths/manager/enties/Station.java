package com.travel.paths.manager.enties;

import lombok.Data;

/**
 * Class representing a station in the travel path system. It contains
 * information about the station's identifier and name.
 * 
 * @author Your Name
 * @version 1.0
 */
@Data
public class Station {

	/**
	 * The unique identifier of the station.
	 */
	private Long id;

	/**
	 * The name of the station.
	 */
	private String name;
}