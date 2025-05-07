package mk.ukim.finki.airbnb.service.domain;

import mk.ukim.finki.airbnb.model.domain.TemporaryReservation;

import java.util.List;

public interface TemporaryReservationService {
    List<TemporaryReservation> findAll();
    TemporaryReservation findById(Long id);
    TemporaryReservation save(TemporaryReservation reservation);
    void delete(Long id);

}
