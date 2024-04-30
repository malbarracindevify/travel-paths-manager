package com.travel.paths.manager.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.travel.paths.manager.dto.PathRequest;
import com.travel.paths.manager.dto.StationRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Interface that defines the contract for managing travel paths. It provides
 * methods for creating stations and paths, as well as retrieving the optimal
 * path between two stations.
 * 
 * @author Marcelo Albarracín
 * @version 1.0
 */
public interface TravelPathsManagerController {

	/**
	 * Service for creating stations.
	 * 
	 * @param station_id the ID of the station to be created
	 * @param request    the request data containing station details
	 * @return a ResponseEntity with a success or error message
	 */
	@Operation(summary = "Service for creating stations")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	public ResponseEntity<Object> createStation(Long station_id, StationRequest request);

	/**
	 * Service for creating paths.
	 * 
	 * @param path_id the ID of the path to be created
	 * @param request the request data containing path details
	 * @return a ResponseEntity with a success or error message
	 */
	@Operation(summary = "Service for creating paths")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	public ResponseEntity<Object> createPath(Long path_id, PathRequest request);

	/**
	 * Service for retrieving the optimal path between two stations.
	 * 
	 * @param source_id      the ID of the source station
	 * @param destination_id the ID of the destination station
	 * @return a ResponseEntity containing the optimal path and its details, or an
	 *         error message if the path cannot be found
	 */
	@Operation(summary = "Service for retrieving the optimal path")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	public ResponseEntity<Object> getOptimalPath(Long source_id, Long destination_id);

}
