package mk.ukim.finki.airbnb.service.application;

import mk.ukim.finki.airbnb.dto.CreateCountryDto;
import mk.ukim.finki.airbnb.dto.DisplayCountryDto;

import java.util.List;

public interface CountryApplicationService {
    DisplayCountryDto create(CreateCountryDto dto);
    DisplayCountryDto update(Long id, CreateCountryDto dto);
    List<DisplayCountryDto> getAll();
    DisplayCountryDto getById(Long id);
    void delete(Long id);
}
