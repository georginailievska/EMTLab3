package mk.ukim.finki.airbnb.service.domain;

import mk.ukim.finki.airbnb.model.domain.Accommodation;
import mk.ukim.finki.airbnb.model.enumerations.Category;
import mk.ukim.finki.airbnb.model.views.AccommodationsByHostView;

import java.util.List;

public interface AccommodationService {
    List<Accommodation> findAll();
    Accommodation findById(Long id);
    Accommodation save(Accommodation accommodation, Long hostId);
    Accommodation update(Long id, Accommodation accommodation, Long hostId);
    void delete(Long id);
    List<Accommodation> filterAll(String hostName, String hostSurname, Category category, Boolean rented, String name, Integer numRooms);
    List<AccommodationsByHostView> getAccommodationsGroupedByHost();
}
