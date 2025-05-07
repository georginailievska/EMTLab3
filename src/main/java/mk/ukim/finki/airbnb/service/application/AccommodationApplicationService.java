package mk.ukim.finki.airbnb.service.application;

import mk.ukim.finki.airbnb.dto.CreateAccommodationDto;
import mk.ukim.finki.airbnb.dto.DisplayAccommodationDto;
import mk.ukim.finki.airbnb.model.enumerations.Category;

import java.util.List;

public interface AccommodationApplicationService {
    DisplayAccommodationDto create(CreateAccommodationDto dto);
    DisplayAccommodationDto update(Long id, CreateAccommodationDto dto);
    List<DisplayAccommodationDto> getAll();
    DisplayAccommodationDto getById(Long id);
    void delete(Long id);
    List<DisplayAccommodationDto> filter(String hostName, String hostSurname, Category category, Boolean rented, String name, Integer numRooms);
}
