package mk.ukim.finki.airbnb.service.domain.impl;

import mk.ukim.finki.airbnb.model.domain.TemporaryReservation;
import mk.ukim.finki.airbnb.model.exceptions.TemporaryReservationNotFoundException;
import mk.ukim.finki.airbnb.repository.TemporaryReservationRepository;
import mk.ukim.finki.airbnb.service.domain.TemporaryReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemporaryReservationServiceImpl implements TemporaryReservationService {

    private final TemporaryReservationRepository temporaryReservationRepository;

    public TemporaryReservationServiceImpl(TemporaryReservationRepository temporaryReservationRepository) {
        this.temporaryReservationRepository = temporaryReservationRepository;
    }

    @Override
    public List<TemporaryReservation> findAll() {
        return temporaryReservationRepository.findAll();
    }

    @Override
    public TemporaryReservation findById(Long id) {
        return temporaryReservationRepository.findById(id)
                .orElseThrow(() -> new TemporaryReservationNotFoundException(id));
    }

    @Override
    public TemporaryReservation save(TemporaryReservation reservation) {
        return temporaryReservationRepository.save(reservation);
    }

    @Override
    public void delete(Long id) {
        temporaryReservationRepository.deleteById(id);
    }
}
