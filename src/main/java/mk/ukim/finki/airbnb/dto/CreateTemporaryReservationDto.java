package mk.ukim.finki.airbnb.dto;

import mk.ukim.finki.airbnb.model.domain.Accommodation;
import mk.ukim.finki.airbnb.model.domain.Host;
import mk.ukim.finki.airbnb.model.domain.TemporaryReservation;
import mk.ukim.finki.airbnb.model.domain.User;

public record CreateTemporaryReservationDto(
        Long userId,
        Long accommodationId,
        Long hostId,
        boolean confirmed
) {
    public TemporaryReservation toReservation(User user, Accommodation accommodation, Host host) {
        return new TemporaryReservation(user, accommodation, host, confirmed);
    }
}
