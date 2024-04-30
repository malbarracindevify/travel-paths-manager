package com.travel.paths.manager.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.paths.manager.controller.TravelPathsManagerController;
import com.travel.paths.manager.dto.OptimalPathResponse;
import com.travel.paths.manager.dto.PathRequest;
import com.travel.paths.manager.dto.StationRequest;
import com.travel.paths.manager.enties.Path;
import com.travel.paths.manager.enties.Station;
import com.travel.paths.manager.services.PathService;
import com.travel.paths.manager.services.StationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of the TravelPathsManagerController interface. This class
 * handles requests related to station and path management. It allows the
 * creation of stations and paths, as well as retrieving the optimal path
 * between two stations.
 * 
 * @author Marcelo Albarracín
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Travel Paths Manager")
public class TravelPathsManagerControllerImpl implements TravelPathsManagerController {

	@Autowired
	private StationService stationService;

	@Autowired
	private PathService pathService;

	/**
	 * Creates a new station with the provided ID and station request data.
	 * 
	 * @param station_id the ID of the station to be created
	 * @param request    the request data containing station details
	 * @return a ResponseEntity with a success or error message
	 */
	@Override
	@PutMapping(value = "/stations/{station_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createStation(@PathVariable Long station_id, @RequestBody StationRequest request) {

		Station station = stationService.createStation(station_id, request);
		if (station != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new OptimalPathResponse("ok"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new OptimalPathResponse("error"));
		}

	}

	/**
	 * Creates a new path with the provided ID and path request data.
	 * 
	 * @param path_id the ID of the path to be created
	 * @param request the request data containing path details
	 * @return a ResponseEntity with a success or error message
	 */
	@Override
	@PutMapping(value = "/paths/{path_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createPath(@PathVariable Long path_id, @RequestBody PathRequest request) {
		Path path = pathService.createPath(path_id, request);
		if (path != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new OptimalPathResponse("ok"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new OptimalPathResponse("error"));
		}
	}

	/**
	 * Retrieves the optimal path between the specified source and destination
	 * stations.
	 * 
	 * @param source_id      the ID of the source station
	 * @param destination_id the ID of the destination station
	 * @return a ResponseEntity containing the optimal path and its details, or an
	 *         error message if the path cannot be found
	 */
	@Override
	@GetMapping(value = "/paths/{source_id}/{destination_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getOptimalPath(@PathVariable Long source_id, @PathVariable Long destination_id) {

		Map<String, Object> path = pathService.getPathsBySourceIdAndDestinationId(source_id, destination_id);
		if (path != null) {
			return ResponseEntity.status(HttpStatus.OK).body(path);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new OptimalPathResponse("error"));
		}
	}

}
