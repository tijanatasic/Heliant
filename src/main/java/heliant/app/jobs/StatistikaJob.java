package heliant.app.jobs;

import heliant.app.service.StatistikaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class StatistikaJob {

    private final StatistikaService statistikaService;

    @Scheduled(cron = "${scheduled.statistika-job.cron}")
    @Transactional
    public void runStatistikaJob() {
        LocalDateTime currentTimestamp = LocalDateTime.now();
        log.info("Starting StatistikaJob at: {}", currentTimestamp);
        statistikaService.executeStatistikaJob(currentTimestamp);
        log.info("Successfully executed StatistikaJob");
    }

}
