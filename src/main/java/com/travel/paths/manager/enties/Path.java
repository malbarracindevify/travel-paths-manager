package com.travel.paths.manager.enties;

import lombok.Data;

/**
 * Class representing a connection between two stations in the path graph. It
 * contains information about the path's identifier, source station, destination
 * station, and the travel cost between them.
 * 
 * @author Your Name
 * @version 1.0
 */
@Data
public class Path {

	/**
	 * The unique identifier of the path.
	 */
	private Long id;

	/**
	 * The identifier of the source station of the path.
	 */
	private Long sourceId;

	/**
	 * The identifier of the destination station of the path.
	 */
	private Long destinationId;

	/**
	 * The travel cost between the source station and the destination station.
	 */
	private double cost;
}
