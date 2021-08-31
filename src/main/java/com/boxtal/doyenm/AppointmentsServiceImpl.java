package com.boxtal.doyenm;

import com.boxtal.doyenm.doctolib.DoctolibService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppointmentsServiceImpl implements AppointmentsService {

    private final DoctolibService doctolibService;

    public List<Appointment> getAppointmentsByZipCode(String zipCode) {
        List<Appointment> appointments = doctolibService.getAppointmentsByZipCode(zipCode);
        // Add appointments from other services (like Keldoc) to the list
// TODO Tri

        return null;
    }
}

