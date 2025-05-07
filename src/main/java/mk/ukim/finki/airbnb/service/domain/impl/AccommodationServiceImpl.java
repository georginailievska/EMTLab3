package mk.ukim.finki.airbnb.service.domain.impl;

import mk.ukim.finki.airbnb.dto.DisplayAccommodationDto;
import mk.ukim.finki.airbnb.model.domain.Accommodation;
import mk.ukim.finki.airbnb.model.domain.Host;
import mk.ukim.finki.airbnb.model.enumerations.Category;
import mk.ukim.finki.airbnb.model.exceptions.AccommodationNotFoundException;
import mk.ukim.finki.airbnb.model.exceptions.HostNotFoundException;
import mk.ukim.finki.airbnb.repository.AccommodationRepository;
import mk.ukim.finki.airbnb.repository.HostRepository;
import mk.ukim.finki.airbnb.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Accommodation findById(Long id) {
        return accommodationRepository.findById(id)
                .orElseThrow(() -> new AccommodationNotFoundException(id));
    }

    @Override
    public Accommodation save(Accommodation accommodation, Long hostId) {
        Host host = hostRepository.findById(hostId)
                .orElseThrow(() -> new HostNotFoundException(hostId));
        accommodation.setHost(host);
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Accommodation update(Long id, Accommodation accommodation, Long hostId) {
        Accommodation existing = findById(id);
        Host host = hostRepository.findById(hostId)
                .orElseThrow(() -> new HostNotFoundException(hostId));

        existing.setName(accommodation.getName());
        existing.setCategory(accommodation.getCategory());
        existing.setNumRooms(accommodation.getNumRooms());
        existing.setRented(accommodation.isRented());
        existing.setHost(host);

        return accommodationRepository.save(existing);
    }

    @Override
    public List<Accommodation> filterAll(String hostName, String hostSurname, Category category, Boolean rented, String name, Integer numRooms) {
        return accommodationRepository.filterAll(hostName, hostSurname, category, rented, name, numRooms);
    }

    @Override
    public void delete(Long id) {
        accommodationRepository.deleteById(id);
    }

}

