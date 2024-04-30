package com.travel.paths.manager.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.paths.manager.dto.StationRequest;
import com.travel.paths.manager.enties.Station;

import lombok.AllArgsConstructor;

/**
 * Service class for managing stations. It provides methods for creating
 * stations.
 * 
 * @author Marcelo Albarracín
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class StationService {

	@Autowired
	private StorageService storageService;

	private final ModelMapper modelMapper;

	/**
	 * Creates a new station with the given ID and request data.
	 * 
	 * @param stationId the ID of the station to be created
	 * @param request   the request data containing station details
	 * @return the created station
	 */
	public Station createStation(Long stationId, StationRequest request) {
		Station station = modelMapper.map(request, Station.class);
		station.setId(stationId);
		return storageService.addStation(station);
	}

}
