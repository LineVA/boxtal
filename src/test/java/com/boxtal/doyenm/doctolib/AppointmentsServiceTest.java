package com.boxtal.doyenm.doctolib;

import com.boxtal.doyenm.Appointment;
import com.boxtal.doyenm.AppointmentsService;
import com.boxtal.doyenm.AppointmentsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppointmentsServiceTest {

    private AppointmentsService subject;

    private DoctolibService doctolibService;

    private String zipCode;

    private Appointment appointment1;
    private Appointment appointment2;

    @BeforeEach
    public void setUp(){
        // Appointment1 is AFTER appointment2
        appointment1 = Appointment.builder().schedule(LocalDateTime.now().plusDays(1)).build();
        appointment2 = Appointment.builder().schedule(LocalDateTime.now()).build();

        doctolibService = mock(DoctolibService.class);

        subject = new AppointmentsServiceImpl(doctolibService);
    }

    @Test
    public void shouldReturnSortedListWhenDoctolibReturnsList(){
        // Given
        when(doctolibService.getAppointmentsByZipCode(zipCode)).thenReturn(Arrays.asList(appointment1, appointment2));
        // When
        List<Appointment> result = subject.getAppointmentsByZipCode(zipCode);
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualTo(appointment2);
        assertThat(result.get(1)).isEqualTo(appointment1);

    }

    @Test
    public void shouldReturnEmptyListWhenDoctolibReturnsEmptyList(){
        // Given
        when(doctolibService.getAppointmentsByZipCode(zipCode)).thenReturn(Collections.EMPTY_LIST);
        // When
        List<Appointment> result = subject.getAppointmentsByZipCode(zipCode);
        // Then
        assertThat(result).isEmpty();
    }
}
