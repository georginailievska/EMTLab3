package mk.ukim.finki.airbnb.service.application;

import mk.ukim.finki.airbnb.dto.CreateHostDto;
import mk.ukim.finki.airbnb.dto.DisplayHostDto;
import mk.ukim.finki.airbnb.model.projections.HostNameProjection;

import java.util.List;

public interface HostApplicationService {
    DisplayHostDto create(CreateHostDto dto);
    DisplayHostDto update(Long id, CreateHostDto dto);
    List<DisplayHostDto> getAll();
    DisplayHostDto getById(Long id);
    void delete(Long id);
    List<HostNameProjection> getAllHostNames();
    List<DisplayHostDto> findAllWithoutTemporaryReservations();
}
