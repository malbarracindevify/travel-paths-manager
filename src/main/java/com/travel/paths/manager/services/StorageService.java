package com.travel.paths.manager.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.paths.manager.enties.Path;
import com.travel.paths.manager.enties.Station;
import com.travel.paths.manager.routeoptimization.FindOptimalPath;

import lombok.AllArgsConstructor;

/**
 * Service class for managing storage of stations and paths. It provides methods
 * for adding stations and paths, and finding optimal paths between stations
 * using Dijkstra's algorithm.
 * 
 * @author Marcelo Albarracín
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class StorageService {

	private List<Station> stations = new ArrayList<>();
	/* Map representing the graph of nodes */
	private Map<Long, Map<Long, Double>> graph;

	@Autowired
	private FindOptimalPath dijkstraAlgorithm;

	/**
	 * Adds a new station to the storage.
	 * 
	 * @param station the station to be added
	 * @return the added station
	 */
	public Station addStation(Station station) {
		stations.add(station);
		return station;
	}

	/**
	 * Adds a new path to the graph.
	 * 
	 * @param path the path to be added
	 * @return the added path
	 * @throws IllegalArgumentException if a path already exists between the source
	 *                                  and destination stations
	 */
	public Path addPath(Path path) {
		// Check if there is already a path between the two stations
		if (graph.containsKey(path.getSourceId())
				&& graph.get(path.getSourceId()).containsKey(path.getDestinationId())) {
			throw new IllegalArgumentException(
					"A path already exists between stations " + path.getSourceId() + " and " + path.getDestinationId());
		}

		/* Add the edge from the source station to the destination station */
		graph.computeIfAbsent(path.getSourceId(), k -> new HashMap<>()).put(path.getDestinationId(), path.getCost());
		/*
		 * Add the edge from the destination station to the origin station (for
		 * bidirectional graphs)
		 */
		graph.computeIfAbsent(path.getDestinationId(), k -> new HashMap<>()).put(path.getSourceId(), path.getCost());

		return path;
	}

	/**
	 * Finds the optimal path between the source and destination stations.
	 * 
	 * @param sourceId      the ID of the source station
	 * @param destinationId the ID of the destination station
	 * @return a map containing the optimal path and the total cost of the trip
	 */
	public Map<String, Object> getPathsBySourceIdAndDestinationId(Long sourceId, Long destinationId) {
		dijkstraAlgorithm.setGraph(graph);
		return dijkstraAlgorithm.findOptimalPath(sourceId, destinationId);
	}

	/**
	 * Sets the graph representing the network of stations and paths.
	 * 
	 * @param graph the graph to be set
	 */
	public void setGraph(Map<Long, Map<Long, Double>> graph) {
		this.graph = graph;
	}
}
