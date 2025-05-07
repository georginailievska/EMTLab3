package mk.ukim.finki.airbnb.dto;

import mk.ukim.finki.airbnb.model.domain.TemporaryReservation;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayTemporaryReservationDto(
        Long id,
        String accommodationName,
        boolean confirmed
) {
    public static DisplayTemporaryReservationDto from(TemporaryReservation res) {
        return new DisplayTemporaryReservationDto(
                res.getId(),
                res.getAccommodation().getName(),
                res.isConfirmed()
        );
    }

    public static List<DisplayTemporaryReservationDto> from(List<TemporaryReservation> reservations) {
        return reservations.stream().map(DisplayTemporaryReservationDto::from).collect(Collectors.toList());
    }
}
