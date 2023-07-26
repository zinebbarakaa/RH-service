package com.giantLink.RH.models.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestHolidayRequest
{
    @Min(value = 1, message = "Le nombre de jours doit être d'au moins 1.")
    private int numberofdays;

    @NotNull(message = "La date de début ne peut pas être vide.")
    @Future(message = "La date de début doit être dans le futur.")
    private Date startDate;

    @NotNull(message = "La date de fin ne peut pas être vide.")
    @Future(message = "La date de fin doit être dans le futur.")
    private Date finishDate;

    @NotNull(message = "La date de retour ne peut pas être vide.")
    @Future(message = "La date de retour doit être dans le futur.")
    private Date returnDate;

    @NotNull(message = "L'ID de l'employé ne peut pas être vide.")
    private Long employee_id;

}
