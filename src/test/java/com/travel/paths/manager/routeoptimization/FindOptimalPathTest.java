package com.travel.paths.manager.routeoptimization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FindOptimalPathTest {

	private FindOptimalPath findOptimalPath;
	private Map<Long, Map<Long, Double>> graph;

	@BeforeEach
	public void setUp() {

		findOptimalPath = new FindOptimalPath();
		this.graph = new HashMap<>();
		addPath(10L, 11L, 50);
		addPath(10L, 12L, 100);
		addPath(10L, 13L, 60);
		addPath(13L, 12L, 20);
		findOptimalPath.setGraph(graph);
	}

	private void addPath(Long sourceId, Long destinationId, double cost) {
		graph.computeIfAbsent(sourceId, k -> new HashMap<>()).put(destinationId, cost);
		graph.computeIfAbsent(destinationId, k -> new HashMap<>()).put(sourceId, cost);

	}

	@Test
	public void testFindOptimalPathSuccess10to11() {
		testOptimalPath(10L, 11L, 50.0);
	}

	@Test
	public void testFindOptimalPathSuccess10to12() {
		testOptimalPath(10L, 12L, 80.0);
	}

	@Test
	public void testFindOptimalPathSuccess10to13() {
		testOptimalPath(10L, 13L, 60.0);
	}

	@Test
	public void testFindOptimalPath_Success12to10() {
		testOptimalPath(12L, 10L, 80.0);
	}

	@Test
	public void testFindOptimalPath_Success13to10() {
		testOptimalPath(13L, 10L, 60.0);
	}

	@Test
	public void testFindOptimalPath_Success12to13() {
		testOptimalPath(12L, 13L, 20.0);
	}

	/*
	 * @Test public void testFindOptimalPath_Failure_NoPath_11_to_12() {
	 * testOptimalPathFailure(9L, 8L); }
	 * 
	 * @Test public void testFindOptimalPath_Failure_NoPath_11_to_13() {
	 * testOptimalPathFailure(3L, 4L); }
	 */

	private void testOptimalPath(Long source, Long destination, double expectedCost) {

		Map<String, Object> result = findOptimalPath.findOptimalPath(source, destination);
		assertNotNull(result);
		assertEquals(expectedCost, (double) result.get("cost"));
	}

	/*
	 * private void testOptimalPathFailure(Long source, Long destination) {
	 * Map<String, Object> result = findOptimalPath.findOptimalPath(source,
	 * destination); assertNull(result); }
	 */
}
