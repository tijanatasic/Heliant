package heliant.app.service;

import java.time.LocalDateTime;

public interface StatistikaService {
    void executeStatistikaJob(LocalDateTime currentTimestamp);
}
