package heliant.app.service.impl;

import heliant.app.entity.StatistikaEntity;
import heliant.app.repository.FormularPopunjenEntityRepository;
import heliant.app.repository.StatistikaEntityRepository;
import heliant.app.service.StatistikaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatistikaServiceImpl implements StatistikaService {

    private final StatistikaEntityRepository statistikaEntityRepository;
    private final FormularPopunjenEntityRepository formularPopunjenEntityRepository;

    @Override
    public void executeStatistikaJob(LocalDateTime currentTimestamp) {
        LocalDateTime dateFrom = currentTimestamp.toLocalDate().minusDays(1).atStartOfDay();
        LocalDateTime dateTo = currentTimestamp.toLocalDate().atStartOfDay().minusNanos(1);
        LocalDate date = dateFrom.toLocalDate();
        log.info("Fetching number of populated formular between {} and {}", dateFrom, dateTo);
        Integer numberOfFormularPopunjen = formularPopunjenEntityRepository.countByVremeKreiranjaBetween(dateFrom, dateTo);
        log.info("Number of populated formular for date: {} is {}", date, numberOfFormularPopunjen);
        StatistikaEntity statistikaEntity = generateStatistikaEntity(date, numberOfFormularPopunjen);
        StatistikaEntity savedStatistikaEntity = statistikaEntityRepository.save(statistikaEntity);
        log.info("Successfully saved statistikaEntity: {}", savedStatistikaEntity);
    }

    private StatistikaEntity generateStatistikaEntity(LocalDate date, Integer numberOfFormularPopunjen) {
        StatistikaEntity statistikaEntity = new StatistikaEntity();
        statistikaEntity.setDatum(date);
        statistikaEntity.setBrojPopunjenihFormulara(numberOfFormularPopunjen);
        return statistikaEntity;
    }
}
