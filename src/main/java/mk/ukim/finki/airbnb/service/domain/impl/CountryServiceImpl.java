package mk.ukim.finki.airbnb.service.domain.impl;

import mk.ukim.finki.airbnb.model.domain.Country;
import mk.ukim.finki.airbnb.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.airbnb.repository.CountryRepository;
import mk.ukim.finki.airbnb.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country update(Long id, Country updated) {
        Country existing = findById(id);
        existing.setName(updated.getName());
        existing.setContinent(updated.getContinent());
        return countryRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
