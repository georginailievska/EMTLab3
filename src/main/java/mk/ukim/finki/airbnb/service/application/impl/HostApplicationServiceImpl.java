package mk.ukim.finki.airbnb.service.application.impl;

import mk.ukim.finki.airbnb.model.projections.HostNameProjection;
import mk.ukim.finki.airbnb.service.application.HostApplicationService;
import mk.ukim.finki.airbnb.model.domain.Country;
import mk.ukim.finki.airbnb.model.domain.Host;
import mk.ukim.finki.airbnb.dto.CreateHostDto;
import mk.ukim.finki.airbnb.dto.DisplayHostDto;
import mk.ukim.finki.airbnb.repository.CountryRepository;
import mk.ukim.finki.airbnb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final CountryRepository countryRepository;

    public HostApplicationServiceImpl(HostService hostService, CountryRepository countryRepository) {
        this.hostService = hostService;
        this.countryRepository = countryRepository;
    }

    @Override
    public DisplayHostDto create(CreateHostDto dto) {
        Country country = countryRepository.findById(dto.countryId()).orElseThrow();
        Host host = dto.toHost(country);
        return DisplayHostDto.from(hostService.save(host));
    }

    @Override
    public List<DisplayHostDto> getAll() {
        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public DisplayHostDto getById(Long id) {
        return DisplayHostDto.from(hostService.findById(id));
    }

    @Override
    public DisplayHostDto update(Long id, CreateHostDto dto) {
        Country country = countryRepository.findById(dto.countryId()).orElseThrow();
        Host updated = dto.toHost(country);
        updated.setId(id);
        return DisplayHostDto.from(hostService.update(id, updated));
    }

    @Override
    public void delete(Long id) {
        hostService.delete(id);
    }

    @Override
    public List<HostNameProjection> getAllHostNames() {
        return hostService.getAllHostNames();
    }

    @Override
    public List<DisplayHostDto> findAllWithoutTemporaryReservations() {
        return DisplayHostDto.from(hostService.findAllWithoutTemporaryReservations());
    }
}
