package mk.ukim.finki.airbnb.listeners;

import mk.ukim.finki.airbnb.events.HostCreatedEvent;
import mk.ukim.finki.airbnb.events.HostUpdatedEvent;
import mk.ukim.finki.airbnb.events.HostDeletedEvent;
import mk.ukim.finki.airbnb.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventListener {

    private final HostService hostService;

    public HostEventListener(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostCreated(HostCreatedEvent event) {
        hostService.refreshHostsByCountryMaterializedView();
    }

    @EventListener
    public void onHostUpdated(HostUpdatedEvent event) {
        hostService.refreshHostsByCountryMaterializedView();
    }

    @EventListener
    public void onHostDeleted(HostDeletedEvent event) {
        hostService.refreshHostsByCountryMaterializedView();
    }
}
