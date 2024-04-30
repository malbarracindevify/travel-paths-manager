package com.travel.paths.manager.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.travel.paths.manager.dto.StationRequest;
import com.travel.paths.manager.enties.Station;

@ExtendWith(MockitoExtension.class)
public class StationServiceTest {

    @Mock
    private StorageService storageService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private StationService stationService;

    @Test
    void testCreateStation() {
        // Arrange
        Long stationId = 1L;        
        StationRequest request = new StationRequest();
        request.setName("Station A");
        Station station = new Station();
        station.setId(stationId);
        station.setName("Station A");

        // Mocking modelMapper behavior
        when(modelMapper.map(request, Station.class)).thenReturn(station);

        // Mocking storageService behavior
        when(storageService.addStation(any(Station.class))).thenReturn(station);

        // Act
        Station createdStation = stationService.createStation(stationId, request);

        // Assert
        assertNotNull(createdStation);
        assertEquals(stationId, createdStation.getId());
        assertEquals(request.getName(), createdStation.getName());

        // Verify
        verify(modelMapper, times(1)).map(request, Station.class);
        verify(storageService, times(1)).addStation(station);
    }
}
