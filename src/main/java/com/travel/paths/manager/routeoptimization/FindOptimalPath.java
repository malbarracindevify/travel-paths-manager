package com.travel.paths.manager.routeoptimization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * This class implements the Dijkstra's algorithm to find the optimal path in a
 * weighted graph. The algorithm finds the shortest path from a source station
 * to a destination station, minimizing the total travel cost.
 *
 * @author Marcelo Albarracín
 * @version 1.0
 */
@Component
public final class FindOptimalPath {

	private Map<Long, Map<Long, Double>> graph;

	/**
	 * Sets the graph representation of the network.
	 *
	 * @param graph the graph representing the network
	 */
	public void setGraph(Map<Long, Map<Long, Double>> graph) {
		this.graph = graph;
	}

	/**
	 * Finds the shortest distances from the source station to all other stations in
	 * the graph using Dijkstra's algorithm.
	 *
	 * @param source the source station from which the shortest distances will be
	 *               calculated
	 * @return a map that assigns each station to its shortest distance from the
	 *         source station
	 */
	public Map<Long, Double> shortestPath(Long source) {
		Map<Long, Double> distances = new HashMap<>();
		Set<Long> visited = new HashSet<>();
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(node -> node.distance));

		distances.put(source, 0.0);
		pq.add(new Node(source, 0.0));

		while (!pq.isEmpty()) {
			Node currentNode = pq.poll();
			Long currentStation = currentNode.station;

			if (visited.contains(currentStation))
				continue;
			visited.add(currentStation);

			for (Map.Entry<Long, Double> neighbor : graph.getOrDefault(currentStation, Collections.emptyMap())
					.entrySet()) {
				Long neighborStation = neighbor.getKey();
				Double cost = neighbor.getValue();
				double distance = distances.getOrDefault(currentStation, Double.MAX_VALUE) + cost;

				if (distance < distances.getOrDefault(neighborStation, Double.MAX_VALUE)) {
					distances.put(neighborStation, distance);
					pq.add(new Node(neighborStation, distance));
				}
			}
		}
		return distances;
	}

	/**
	 * Finds the optimal path from the source station to the destination station,
	 * minimizing the total travel cost using Dijkstra's algorithm.
	 *
	 * @param source      the source station from which the optimal path will start
	 * @param destination the destination station to which the optimal path will
	 *                    lead
	 * @return a map containing the optimal path and the total travel cost
	 */
	public Map<String, Object> findOptimalPath(Long source, Long destination) {
		Map<Long, Double> distances = shortestPath(source);
		List<Long> path = new ArrayList<>();
		Long currentStation = destination;
		double totalCost = 0;

		while (currentStation != null && !currentStation.equals(source)) {
			path.add(currentStation);
			Long nextStation = getParentStation(currentStation, distances);

			if (nextStation == null)
				break;

			double cost = graph.getOrDefault(nextStation, Collections.emptyMap()).getOrDefault(currentStation,
					Double.MAX_VALUE);
			if (Double.isInfinite(cost))
				break;

			totalCost += cost;
			currentStation = nextStation;
		}

		if (currentStation == null)
			return null;

		path.add(source);
		Collections.reverse(path);

		Map<String, Object> result = new HashMap<>();
		result.put("path", path);
		result.put("cost", totalCost);
		return result;
	}

	/**
	 * Gets the parent station of a given station in the optimal path calculated by
	 * Dijkstra's algorithm.
	 *
	 * @param station   the station whose parent station is to be obtained
	 * @param distances a map that assigns each station its shortest distance from
	 *                  the source station
	 * @return the parent station of the given station, or null if the given station
	 *         has no parent station
	 */
	private Long getParentStation(Long station, Map<Long, Double> distances) {
		return distances.entrySet().stream()
				.filter(entry -> entry.getValue()
						.equals(distances.get(station) - graph.getOrDefault(entry.getKey(), Collections.emptyMap())
								.getOrDefault(station, Double.MAX_VALUE)))
				.map(Map.Entry::getKey).findFirst().orElse(null);
	}

	/**
	 * This is an inner class representing a node in the priority queue used in
	 * Dijkstra's algorithm.
	 */
	private static class Node {
		Long station; // Identifier of the station
		Double distance; // Accumulated distance from the source station

		/**
		 * Constructs a node with the given station identifier and accumulated distance.
		 *
		 * @param station  the identifier of the station
		 * @param distance the accumulated distance from the source station
		 */
		public Node(Long station, Double distance) {
			this.station = station;
			this.distance = distance;
		}
	}
}