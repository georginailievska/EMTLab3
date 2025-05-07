package mk.ukim.finki.airbnb.jobs;

import mk.ukim.finki.airbnb.repository.AccommodationsByHostViewRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final AccommodationsByHostViewRepository  accommodationsByHostViewRepository;

    public ScheduledTasks(AccommodationsByHostViewRepository accommodationsByHostViewRepository) {
        this.accommodationsByHostViewRepository = accommodationsByHostViewRepository;
    }

    @Scheduled(cron = "0 0 0 * * *") // Daily at midnight
    public void refreshMaterializedView() {
        this.accommodationsByHostViewRepository.refreshMaterializedView();
    }
}
