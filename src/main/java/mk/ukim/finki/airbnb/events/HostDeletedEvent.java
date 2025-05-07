package mk.ukim.finki.airbnb.events;

import mk.ukim.finki.airbnb.model.domain.Host;
import org.springframework.context.ApplicationEvent;

public class HostDeletedEvent extends ApplicationEvent {
    public HostDeletedEvent(Host source) {
        super(source);
    }
}
