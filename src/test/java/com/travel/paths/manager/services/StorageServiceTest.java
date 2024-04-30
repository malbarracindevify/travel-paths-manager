package com.travel.paths.manager.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.travel.paths.manager.enties.Path;
import com.travel.paths.manager.routeoptimization.FindOptimalPath;

public class StorageServiceTest {

	@Mock
	private FindOptimalPath findOptimalPath;

	@InjectMocks
	private StorageService storageService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddPathSuccess() {
		Path path = new Path();
		path.setId(1L);
		path.setSourceId(10L);
		path.setDestinationId(20L);
		path.setCost(50.0);

		Map<Long, Map<Long, Double>> graph = new HashMap<>();

		when(findOptimalPath.shortestPath(anyLong())).thenReturn(new HashMap<>());
		when(findOptimalPath.findOptimalPath(anyLong(), anyLong())).thenReturn(new HashMap<>());

		storageService.setGraph(graph);
		assertEquals(path, storageService.addPath(path));
	}

	@Test
	void testAddPathAlreadyExists() {
		Path path = new Path();
		path.setId(1L);
		path.setSourceId(10L);
		path.setDestinationId(20L);
		path.setCost(50.0);

		Map<Long, Map<Long, Double>> graph = new HashMap<>();
		graph.put(10L, new HashMap<>());
		graph.get(10L).put(20L, 50.0);
		storageService.setGraph(graph);
		assertThrows(IllegalArgumentException.class, () -> storageService.addPath(path));
	}

	@Test
	void testGetPathsBySourceIdAndDestinationId() {
		Map<String, Object> result = new HashMap<>();
		result.put("path", new long[] { 1L, 2L, 3L });
		result.put("cost", 100.0);
		when(findOptimalPath.findOptimalPath(anyLong(), anyLong())).thenReturn(result);
		assertEquals(result, storageService.getPathsBySourceIdAndDestinationId(1L, 3L));
	}
}