package mk.ukim.finki.airbnb.service.domain;

import mk.ukim.finki.airbnb.model.domain.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country findById(Long id);
    Country save(Country country);
    Country update(Long id, Country updated);
    void delete(Long id);
}
