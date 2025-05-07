package mk.ukim.finki.airbnb.events;

import mk.ukim.finki.airbnb.model.domain.Host;
import org.springframework.context.ApplicationEvent;

public class HostUpdatedEvent extends ApplicationEvent {
    public HostUpdatedEvent(Host source) {
        super(source);
    }
}
