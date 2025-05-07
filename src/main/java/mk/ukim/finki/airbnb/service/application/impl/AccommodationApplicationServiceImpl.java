package mk.ukim.finki.airbnb.service.application.impl;

import mk.ukim.finki.airbnb.model.enumerations.Category;
import mk.ukim.finki.airbnb.service.application.AccommodationApplicationService;
import mk.ukim.finki.airbnb.model.domain.Accommodation;
import mk.ukim.finki.airbnb.model.domain.Host;
import mk.ukim.finki.airbnb.dto.CreateAccommodationDto;
import mk.ukim.finki.airbnb.dto.DisplayAccommodationDto;
import mk.ukim.finki.airbnb.repository.HostRepository;
import mk.ukim.finki.airbnb.service.domain.AccommodationService;
import org.springframework.stereotype.Service;
import mk.ukim.finki.airbnb.model.views.AccommodationsByHostView;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostRepository hostRepository;

    public AccommodationApplicationServiceImpl(
            AccommodationService accommodationService,
            HostRepository hostRepository
    ) {
        this.accommodationService = accommodationService;
        this.hostRepository = hostRepository;
    }

    @Override
    public DisplayAccommodationDto create(CreateAccommodationDto dto) {
        Host host = hostRepository.findById(dto.hostId()).orElseThrow();
        Accommodation accommodation = dto.toAccommodation(host);
        return DisplayAccommodationDto.from(accommodationService.save(accommodation, host.getId()));
    }

    @Override
    public DisplayAccommodationDto update(Long id, CreateAccommodationDto dto) {
        Host host = hostRepository.findById(dto.hostId()).orElseThrow();
        Accommodation accommodation = dto.toAccommodation(host);
        accommodation.setId(id);
        accommodation.setRented(dto.rented());
        return DisplayAccommodationDto.from(accommodationService.update(id, accommodation, host.getId()));
    }

    @Override
    public List<DisplayAccommodationDto> getAll() {
        return accommodationService.findAll().stream()
                .map(DisplayAccommodationDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public DisplayAccommodationDto getById(Long id) {
        return DisplayAccommodationDto.from(accommodationService.findById(id));
    }

    @Override
    public List<DisplayAccommodationDto> filter(String hostName, String hostSurname, Category category, Boolean rented, String name, Integer numRooms) {
        List<Accommodation> results = accommodationService.filterAll(hostName, hostSurname, category, rented, name, numRooms);
        return DisplayAccommodationDto.from(results);
    }

    @Override
    public void delete(Long id) {
        accommodationService.delete(id);
    }

    @Override
    public List<AccommodationsByHostView> getAccommodationsGroupedByHost() {
        return accommodationService.getAccommodationsGroupedByHost();
    }

}

