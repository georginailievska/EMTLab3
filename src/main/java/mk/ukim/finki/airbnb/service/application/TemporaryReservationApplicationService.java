package mk.ukim.finki.airbnb.service.application;

import mk.ukim.finki.airbnb.dto.DisplayTemporaryReservationDto;

import java.util.List;

public interface TemporaryReservationApplicationService {
    void addToTempList(Long accommodationId, String username);
    List<DisplayTemporaryReservationDto> viewTempList(String username);
    void confirmAll(String username);
}
