package com.boxtal.doyenm.doctolib.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize("availabilities")
public class AppointmentDto{
    List<Availability> availabilities;
    Structure structure;
}

class Availability {
    String date;
    List<Slot> slots;
}

class Slot {
    String startDate;
}

