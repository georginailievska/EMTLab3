package mk.ukim.finki.airbnb.events;

import mk.ukim.finki.airbnb.model.domain.Host;
import org.springframework.context.ApplicationEvent;

public class HostCreatedEvent extends ApplicationEvent {
    public HostCreatedEvent(Host source) {
        super(source);
    }
}
