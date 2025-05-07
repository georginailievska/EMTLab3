package mk.ukim.finki.airbnb.service.application.impl;

import mk.ukim.finki.airbnb.dto.DisplayTemporaryReservationDto;
import mk.ukim.finki.airbnb.model.domain.Accommodation;
import mk.ukim.finki.airbnb.model.domain.TemporaryReservation;
import mk.ukim.finki.airbnb.model.domain.User;
import mk.ukim.finki.airbnb.model.exceptions.*;
import mk.ukim.finki.airbnb.repository.AccommodationRepository;
import mk.ukim.finki.airbnb.repository.TemporaryReservationRepository;
import mk.ukim.finki.airbnb.repository.UserRepository;
import mk.ukim.finki.airbnb.service.application.TemporaryReservationApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemporaryReservationApplicationServiceImpl implements TemporaryReservationApplicationService {

    private final UserRepository userRepo;
    private final AccommodationRepository accRepo;
    private final TemporaryReservationRepository tempRepo;

    public TemporaryReservationApplicationServiceImpl(
            UserRepository userRepo,
            AccommodationRepository accRepo,
            TemporaryReservationRepository tempRepo
    ) {
        this.userRepo = userRepo;
        this.accRepo = accRepo;
        this.tempRepo = tempRepo;
    }

    @Override
    public void addToTempList(Long accommodationId, String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        Accommodation acc = accRepo.findById(accommodationId)
                .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));

        if (acc.isRented()) {
            throw new AccommodationAlreadyRentedException(accommodationId);
        }

        boolean alreadyAdded = tempRepo.existsByUserAndAccommodationAndConfirmedFalse(user, acc);
        if (alreadyAdded) {
            throw new DuplicateTemporaryReservationException(accommodationId);
        }

        TemporaryReservation reservation = new TemporaryReservation(user, acc, acc.getHost(), false);
        tempRepo.save(reservation);
    }

    @Override
    public List<DisplayTemporaryReservationDto> viewTempList(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        List<TemporaryReservation> reservations = tempRepo.findAllByUserAndConfirmedFalse(user);
        return DisplayTemporaryReservationDto.from(reservations);
    }

    @Override
    public void confirmAll(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        List<TemporaryReservation> reservations = tempRepo.findAllByUserAndConfirmedFalse(user);

        for (TemporaryReservation res : reservations) {
            Accommodation acc = res.getAccommodation();
            acc.setRented(true);
            res.setConfirmed(true);

            accRepo.save(acc);
            tempRepo.save(res);
        }
    }
}
