package com.power.plant.service;

import com.power.plant.entity.BatteryEntity;
import com.power.plant.mapper.BatteryMapper;
import com.power.plant.model.Battery;
import com.power.plant.model.BatteryResponse;
import com.power.plant.model.BatteryResponse.Statistics;
import com.power.plant.model.BatteryResponse.Statistics.StatisticsBuilder;
import com.power.plant.repository.BatteryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PowerPlantService {


  private final  BatteryRepository batteryRepository;

  private final BatteryMapper batteryMapper;

  public List<Battery> addBatteries(List<Battery> batteryList) {
    var entities = batteryRepository.saveAll(batteryMapper.modelToEntity(batteryList));
    return batteryMapper.entityToModel(entities);
  }


  public BatteryResponse getBatteriesByPostalCodeRange(Integer startPostalCode,
      Integer endPostalCode) {
    var entities = batteryRepository.findAllByPostalCodeLessThanEqualAndPostalCodeGreaterThanEqual(
        endPostalCode, startPostalCode);
    BatteryResponse.BatteryResponseBuilder builder = BatteryResponse.builder();
    List<String> names = entities.stream().map(BatteryEntity::getName).sorted().toList();
    Statistics.StatisticsBuilder statisticsBuilder = getStatisticsBuilder(entities);
    return builder.names(names).statistics(statisticsBuilder.build()).build();
  }

  private StatisticsBuilder getStatisticsBuilder(List<BatteryEntity> entities) {
    var statistics = entities.stream().mapToInt(BatteryEntity::getWattCapacity)
        .summaryStatistics();
    StatisticsBuilder statisticsBuilder = Statistics.builder();
    statisticsBuilder.total(statistics.getSum());
    statisticsBuilder.averageWatts(statistics.getAverage());
    return statisticsBuilder;
  }

}
