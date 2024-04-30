package com.travel.paths.manager.services;

import com.travel.paths.manager.dto.PathRequest;
import com.travel.paths.manager.enties.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PathServiceTest {

    @Mock
    private StorageService storageService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PathService pathService;

    @Test
    void testCreatePath() {
        // Arrange
        Long pathId = 1L;

        PathRequest request = new PathRequest();
        request.setSourceId(10L);
        request.setDestinationId(20L);
        request.setCost(50.0);
        
        Path path = new Path();
        path.setId(pathId);
        path.setSourceId(10L);
        path.setDestinationId(20L);
        path.setCost(50.0);

        // Mocking modelMapper behavior
        when(modelMapper.map(request, Path.class)).thenReturn(path);

        // Mocking storageService behavior
        when(storageService.addPath(any(Path.class))).thenReturn(path);

        // Act
        Path createdPath = pathService.createPath(pathId, request);

        // Assert
        assertNotNull(createdPath);
        assertEquals(pathId, createdPath.getId());
        assertEquals(request.getSourceId(), createdPath.getSourceId());
        assertEquals(request.getDestinationId(), createdPath.getDestinationId());
        assertEquals(request.getCost(), createdPath.getCost());

        // Verify
        verify(modelMapper, times(1)).map(request, Path.class);
        verify(storageService, times(1)).addPath(path);
    }

    @Test
    void testGetPathsBySourceIdAndDestinationId() {
        // Arrange
        Long sourceId = 10L;
        Long destinationId = 20L;
        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("path", new long[]{1L, 2L, 3L});
        expectedResult.put("cost", 100.0);

        // Mocking storageService behavior
        when(storageService.getPathsBySourceIdAndDestinationId(sourceId, destinationId)).thenReturn(expectedResult);

        // Act
        Map<String, Object> result = pathService.getPathsBySourceIdAndDestinationId(sourceId, destinationId);

        // Assert
        assertEquals(expectedResult, result);

        // Verify
        verify(storageService, times(1)).getPathsBySourceIdAndDestinationId(sourceId, destinationId);
    }
}