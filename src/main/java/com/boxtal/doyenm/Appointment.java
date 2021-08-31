package com.boxtal.doyenm;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class Appointment {
   private String structure;
   private String address;
   private LocalDateTime schedule;
}
