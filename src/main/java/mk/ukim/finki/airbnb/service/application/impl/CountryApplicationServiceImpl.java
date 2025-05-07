package mk.ukim.finki.airbnb.service.application.impl;

import mk.ukim.finki.airbnb.service.application.CountryApplicationService;
import mk.ukim.finki.airbnb.model.domain.Country;
import mk.ukim.finki.airbnb.dto.CreateCountryDto;
import mk.ukim.finki.airbnb.dto.DisplayCountryDto;
import mk.ukim.finki.airbnb.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public DisplayCountryDto create(CreateCountryDto dto) {
        Country country = dto.toCountry();
        return DisplayCountryDto.from(countryService.save(country));
    }

    @Override
    public List<DisplayCountryDto> getAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public DisplayCountryDto getById(Long id) {
        return DisplayCountryDto.from(countryService.findById(id));
    }

    @Override
    public DisplayCountryDto update(Long id, CreateCountryDto dto) {
        Country updated = dto.toCountry();
        return DisplayCountryDto.from(countryService.update(id, updated));
    }

    @Override
    public void delete(Long id) {
        countryService.delete(id);
    }
}

