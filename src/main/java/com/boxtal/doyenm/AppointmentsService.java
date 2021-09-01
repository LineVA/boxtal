package com.boxtal.doyenm;

import java.util.List;

/**
 * Service step
 */
public interface AppointmentsService {

    /**
     * Get all the available appointments from all the platforms (Doctolib, KelDoc...) sorted by dates
     * @param zipCode the zipCode
     * @return all the available appointments
     */
    List<Appointment> getAppointmentsByZipCode(String zipCode);
}

