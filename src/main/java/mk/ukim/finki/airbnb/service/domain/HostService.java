package mk.ukim.finki.airbnb.service.domain;

import mk.ukim.finki.airbnb.model.domain.Host;
import mk.ukim.finki.airbnb.model.projections.HostNameProjection;
import mk.ukim.finki.airbnb.model.views.HostsByCountryView;

import java.util.List;

public interface HostService {
    List<Host> findAll();
    Host findById(Long id);
    Host save(Host host);
    Host update(Long id, Host updated);
    void delete(Long id);
    void refreshHostsByCountryMaterializedView();
    List<HostNameProjection> getAllHostNames();
    List<Host> findAllWithoutTemporaryReservations();
    List<HostsByCountryView> getHostsGroupedByCountry();
}
