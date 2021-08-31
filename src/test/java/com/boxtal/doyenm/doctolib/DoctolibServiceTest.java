package com.boxtal.doyenm.doctolib;

import com.boxtal.doyenm.Appointment;
import com.boxtal.doyenm.doctolib.dto.AppointmentDto;
import com.boxtal.doyenm.doctolib.dto.StructureDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DoctolibServiceTest {

    private DoctolibService subject;

    // Dependencies
    private DoctolibConnector connector;
    private DoctolibToCommonAppointmentsFunction function;

    // Inputs
    private String zipCode;

    private StructureDto structureDto;
    private String structureId;
    private AppointmentDto appointmentDto;
    private List<Appointment> appointments;

    @BeforeEach
    public void setUp(){
        structureId = RandomStringUtils.randomAlphanumeric(10);
        structureDto = new StructureDto(structureId, RandomStringUtils.randomAlphanumeric(10));

        appointmentDto = new AppointmentDto();
        appointments = Collections.emptyList();

        zipCode = RandomStringUtils.randomAlphanumeric(10);

        connector = mock(DoctolibConnector.class);
        function = mock(DoctolibToCommonAppointmentsFunction.class);

        subject = new DoctolibServiceImpl(connector, function);
    }

    @Test
    public void shouldDelegateOperations(){
        // Given
        when(connector.getStructures(zipCode)).thenReturn(Arrays.asList(structureDto, structureDto));
        when(connector.getAppointmentsByStructure(structureDto)).thenReturn(appointmentDto);
        when(function.apply(appointmentDto)).thenReturn(appointments);
        // When
        List<Appointment> result = subject.getAppointmentsByZipCode(zipCode);
        // Then
        assertThat(result).isEqualTo(appointments);
        verify(connector, times(1)).getStructures(zipCode);
        verify(connector, times(2)).getAppointmentsByStructure(structureDto);
        verify(function, times(2)).apply(appointmentDto);

    }

}
