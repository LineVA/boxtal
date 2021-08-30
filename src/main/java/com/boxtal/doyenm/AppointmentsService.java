package com.boxtal.doyenm;

import java.util.List;

public interface AppointmentsService {

    List<Appointment> getAppointmentsByZipCode(String zipCode);
}

