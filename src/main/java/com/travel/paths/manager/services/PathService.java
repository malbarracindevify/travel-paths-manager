package com.travel.paths.manager.services;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.paths.manager.dto.PathRequest;
import com.travel.paths.manager.enties.Path;

import lombok.AllArgsConstructor;

/**
 * Service class for managing paths. It provides methods for creating paths and
 * retrieving paths by source and destination IDs.
 * 
 * @author Marcelo Albarracín
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class PathService {

	@Autowired
	private StorageService storageService;

	private final ModelMapper modelMapper;

	/**
	 * Creates a new path with the given ID and request data.
	 * 
	 * @param pathId  the ID of the path to be created
	 * @param request the request data containing path details
	 * @return the created path
	 */
	public Path createPath(Long pathId, PathRequest request) {
		Path path = modelMapper.map(request, Path.class);
		path.setId(pathId);
		return storageService.addPath(path);
	}

	/**
	 * Retrieves paths between a source and destination station.
	 * 
	 * @param sourceId      the ID of the source station
	 * @param destinationId the ID of the destination station
	 * @return a map containing the paths between the specified source and
	 *         destination stations
	 */
	public Map<String, Object> getPathsBySourceIdAndDestinationId(Long sourceId, Long destinationId) {
		return storageService.getPathsBySourceIdAndDestinationId(sourceId, destinationId);
	}
}
