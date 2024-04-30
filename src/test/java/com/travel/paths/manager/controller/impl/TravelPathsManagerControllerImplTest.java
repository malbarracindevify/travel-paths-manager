package com.travel.paths.manager.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.travel.paths.manager.dto.OptimalPathResponse;
import com.travel.paths.manager.dto.PathRequest;
import com.travel.paths.manager.dto.StationRequest;
import com.travel.paths.manager.enties.Path;
import com.travel.paths.manager.enties.Station;
import com.travel.paths.manager.services.PathService;
import com.travel.paths.manager.services.StationService;

@ExtendWith(MockitoExtension.class)
public class TravelPathsManagerControllerImplTest {

	@Mock
	private StationService stationService;

	@Mock
	private PathService pathService;

	@InjectMocks
	private TravelPathsManagerControllerImpl controller;

	@Test
	void testCreateStationSuccess() {
		// Arrange
		Long stationId = 1L;
		StationRequest request = new StationRequest();
		request.setName("New York");

		Station station = new Station();
		station.setId(stationId);
		station.setName("New York");
		when(stationService.createStation(eq(stationId), eq(request))).thenReturn(station);

		// Act
		ResponseEntity<Object> response = controller.createStation(stationId, request);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testCreateStationFailure() {
		// Arrange
		Long stationId = 1L;
		StationRequest request = new StationRequest();
		request.setName("New York");
		when(stationService.createStation(eq(stationId), eq(request))).thenReturn(null);

		// Act
		ResponseEntity<Object> response = controller.createStation(stationId, request);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	@Test
	void testCreatePathSuccess() {
		// Arrange
		Long pathId = 1L;
		PathRequest request = new PathRequest();

		request.setId(pathId);
		request.setSourceId(10L);
		request.setDestinationId(11L);
		request.setCost(50.0);

		Path path = new Path();
		path.setId(pathId);
		path.setSourceId(10L);
		path.setDestinationId(11L);
		path.setCost(50.0);

		when(pathService.createPath(eq(pathId), eq(request))).thenReturn(path);

		// Act
		ResponseEntity<Object> response = controller.createPath(pathId, request);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	void testCreatePathFailure() {
		// Arrange
		Long pathId = 1L;

		PathRequest request = new PathRequest();

		request.setId(pathId);
		request.setSourceId(10L);
		request.setDestinationId(11L);
		request.setCost(50.0);

		when(pathService.createPath(eq(pathId), eq(request))).thenReturn(null);

		// Act
		ResponseEntity<Object> response = controller.createPath(pathId, request);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("error", ((OptimalPathResponse) response.getBody()).getStatus());
	}

	@Test
	void testGetOptimalPathSuccess() {
		// Arrange
		Long sourceId = 10L;
		Long destinationId = 11L;
		Map<String, Object> path = new HashMap<>();
		path.put("path", List.of(10L, 11L));
		path.put("cost", 50.0);
		when(pathService.getPathsBySourceIdAndDestinationId(eq(sourceId), eq(destinationId))).thenReturn(path);

		// Act
		ResponseEntity<Object> response = controller.getOptimalPath(sourceId, destinationId);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(path, response.getBody());
	}

	@Test
	void testGetOptimalPathFailure() {
		// Arrange
		Long sourceId = 10L;
		Long destinationId = 11L;
		when(pathService.getPathsBySourceIdAndDestinationId(eq(sourceId), eq(destinationId))).thenReturn(null);

		// Act
		ResponseEntity<Object> response = controller.getOptimalPath(sourceId, destinationId);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("error", ((OptimalPathResponse) response.getBody()).getStatus());
	}
}