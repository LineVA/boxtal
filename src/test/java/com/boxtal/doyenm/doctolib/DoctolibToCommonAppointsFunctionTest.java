package com.boxtal.doyenm.doctolib;

import com.boxtal.doyenm.Appointment;
import com.boxtal.doyenm.doctolib.dto.AppointmentDto;
import com.boxtal.doyenm.doctolib.dto.AvailabilityDto;
import com.boxtal.doyenm.doctolib.dto.SlotDto;
import com.boxtal.doyenm.doctolib.dto.StructureDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DoctolibToCommonAppointsFunctionTest {

    private DoctolibToCommonAppointmentsFunction subject;

    // Inputs
    private StructureDto structure;
    private String structureName;
    private String appointments1;
    private String appointments2;
    private AppointmentDto input;


    @BeforeEach
    public void setUp(){
        structureName = RandomStringUtils.randomAlphanumeric(10);
        structure = StructureDto.builder()
                .name(structureName)
                .build();
        appointments1 = "2021-01-01T01:01:01.000+02:00";
        appointments2 = "2022-02-02T02:02:02.000+02:00";
        input = new AppointmentDto();


        subject = new DoctolibToCommonAppointmentsFunction();
    }

    @Test
    public void shouldReturnListOfAppointmentsAccordingToInput(){
        // Given
        input.setStructure(structure);
        input.setAvailabilities(Arrays.asList(new AvailabilityDto(Arrays.asList(new SlotDto(appointments1), new SlotDto(appointments2)))));
        // When
        List<Appointment> result = subject.apply(input);
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);

        assertThat(result.get(0)).isNotNull();
        assertThat(result.get(0).getStructure()).isEqualTo(structureName);
        assertThat(result.get(0).getSchedule()).isEqualTo(LocalDateTime.parse(appointments1.substring(0, 23)));

        assertThat(result.get(1)).isNotNull();
        assertThat(result.get(1).getStructure()).isEqualTo(structureName);
        assertThat(result.get(1).getSchedule()).isEqualTo(LocalDateTime.parse(appointments2.substring(0, 23)));
    }

    @Test
    public void shouldReturnEmptyListOfAppointmentsWhenNoScheduleInInput(){
        // Given
        input.setStructure(structure);
        input.setAvailabilities(Arrays.asList(new AvailabilityDto(Arrays.asList())));
        // When
        List<Appointment> result = subject.apply(input);
        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}
