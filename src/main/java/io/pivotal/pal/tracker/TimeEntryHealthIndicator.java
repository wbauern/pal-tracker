package io.pivotal.pal.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TimeEntryHealthIndicator implements HealthIndicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeEntryHealthIndicator.class);
    
    private static final int MAX_TIME_ENTRIES = 5;
    private final JdbcTimeEntryRepository timeEntryRepo;

    public TimeEntryHealthIndicator(JdbcTimeEntryRepository timeEntryRepo) {
        this.timeEntryRepo = timeEntryRepo;
    }

    @Override
    public Health health() {
        Health.Builder builder = new Health.Builder();

        LOGGER.info("Number of entries: {}", timeEntryRepo.list().size());
        if(timeEntryRepo.list().size() < MAX_TIME_ENTRIES) {
            return builder.up().build();
        } 
        return builder.down().build();
    }
}
