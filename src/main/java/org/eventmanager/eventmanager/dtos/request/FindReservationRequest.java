package org.eventmanager.eventmanager.dtos.request;

import lombok.Data;
import org.eventmanager.eventmanager.Data.model.Category;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
@Data
public class FindReservationRequest {
    String name;
    String email;
    Category category;
}
