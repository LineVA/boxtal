package com.boxtal.doyenm.doctolib;

import com.boxtal.doyenm.Appointment;
import com.boxtal.doyenm.doctolib.dto.AppointmentDto;
import com.boxtal.doyenm.doctolib.dto.SlotDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Function to convert an {{{@link AppointmentDto}}} to a list of {{{@link Appointment}}}
 */
@Component
public class DoctolibToCommonAppointmentsFunction implements Function<AppointmentDto, List<Appointment>> {
    @Override
    public List<Appointment> apply(AppointmentDto appointmentDto) {
        List<SlotDto> slots = new ArrayList<>();
        if (appointmentDto.getAvailabilities() != null) {
            appointmentDto.getAvailabilities()
                    .stream()
                    .map(availability -> availability.getSlots())
                    .forEach(i -> slots.addAll(i));

            return slots.stream()
                    .map(slot -> Appointment.builder()
                            .structure(appointmentDto.getStructure().getName())
                            .schedule(LocalDateTime.parse(slot.getStartDate().substring(0, 23)))
                            .build()
                    ).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
