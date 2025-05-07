package mk.ukim.finki.airbnb.service.domain.impl;

import mk.ukim.finki.airbnb.model.domain.Host;
import mk.ukim.finki.airbnb.model.exceptions.HostNotFoundException;
import mk.ukim.finki.airbnb.model.projections.HostNameProjection;
import mk.ukim.finki.airbnb.repository.HostRepository;
import mk.ukim.finki.airbnb.repository.HostsByCountryViewRepository;
import mk.ukim.finki.airbnb.service.domain.HostService;
import mk.ukim.finki.airbnb.events.HostCreatedEvent;
import mk.ukim.finki.airbnb.events.HostUpdatedEvent;
import mk.ukim.finki.airbnb.events.HostDeletedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import mk.ukim.finki.airbnb.model.views.HostsByCountryView;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final HostsByCountryViewRepository hostsByCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public HostServiceImpl(HostRepository hostRepository, HostsByCountryViewRepository hostsByCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.hostsByCountryViewRepository = hostsByCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Host findById(Long id) {
        return hostRepository.findById(id)
                .orElseThrow(() -> new HostNotFoundException(id));
    }

    @Override
    public Host save(Host host) {
        Host savedHost = hostRepository.save(host);

        // Publish the event after the host is created
        applicationEventPublisher.publishEvent(new HostCreatedEvent(savedHost));

        return savedHost;
    }

    @Override
    public Host update(Long id, Host updated) {
        Host existing = findById(id);
        existing.setName(updated.getName());
        existing.setSurname(updated.getSurname());
        existing.setCountry(updated.getCountry());

        Host savedHost = hostRepository.save(existing);

        // Publish the event after the host is updated
        applicationEventPublisher.publishEvent(new HostUpdatedEvent(savedHost));

        return savedHost;
    }

    @Override
    public void delete(Long id) {
        Host host = findById(id);
        hostRepository.deleteById(id);

        // Publish the event after the host is deleted
        applicationEventPublisher.publishEvent(new HostDeletedEvent(host));
    }

    @Override
    public void refreshHostsByCountryMaterializedView() {
        hostsByCountryViewRepository.refreshView();
    }

    @Override
    public List<HostNameProjection> getAllHostNames() {
        return hostRepository.findAllHostNames();
    }

    @Override
    public List<Host> findAllWithoutTemporaryReservations() {
        return hostRepository.findAllWithoutTemporaryReservations();
    }

    @Override
    public List<HostsByCountryView> getHostsGroupedByCountry() {
        return hostsByCountryViewRepository.findAll();
    }

}
