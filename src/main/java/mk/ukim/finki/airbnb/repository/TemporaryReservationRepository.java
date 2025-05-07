package mk.ukim.finki.airbnb.repository;

import mk.ukim.finki.airbnb.model.domain.Accommodation;
import mk.ukim.finki.airbnb.model.domain.User;
import mk.ukim.finki.airbnb.model.domain.TemporaryReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemporaryReservationRepository extends JpaRepository<TemporaryReservation, Long> {
    List<TemporaryReservation> findAllByUserAndConfirmedFalse(User user);
    boolean existsByUserAndAccommodationAndConfirmedFalse(User user, Accommodation acc);
}
