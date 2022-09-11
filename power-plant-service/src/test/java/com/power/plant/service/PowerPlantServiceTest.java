package com.power.plant.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.power.plant.entity.BatteryEntity;
import com.power.plant.mapper.BatteryMapper;
import com.power.plant.model.Battery;
import com.power.plant.model.BatteryResponse.Statistics;
import com.power.plant.repository.BatteryRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PowerPlantServiceTest {


  @Mock
  BatteryRepository batteryRepository;

  @Mock
  BatteryMapper batteryMapper;

  PowerPlantService powerPlantService;

  @BeforeEach
  void beforeEach() {
    powerPlantService = new PowerPlantService(batteryRepository, batteryMapper);
  }



  @Test
  void addBatteries_Success() {
    var entity1 = BatteryEntity.builder().batteryId(123L).name("Exide")
        .postalCode(123).wattCapacity(100).build();
    var input = Battery.builder().name("Exide")
        .postalCode(123).wattCapacity(100).build();

    when(batteryRepository.saveAll(any())).thenReturn(List.of(entity1));
    when(batteryMapper.entityToModel(List.of(entity1))).thenReturn(List.of(input));

    var actual = powerPlantService.addBatteries(List.of(input));
    assertThat(actual.get(0).getName()).isEqualTo(input.getName());
  }


  @Test
  void getBatteriesByPostalCodeRange_Success() {
    var entity1 = BatteryEntity.builder().batteryId(123L).name("Exide")
        .postalCode(1001).wattCapacity(100).build();

    when(batteryRepository
        .findAllByPostalCodeLessThanEqualAndPostalCodeGreaterThanEqual(
            1010,1001))
        .thenReturn(List.of(entity1));
    var actual = powerPlantService.getBatteriesByPostalCodeRange(1001,1010);
    assertThat(actual).isNotNull();
  }

  @Test
  void getBatteriesByPostalCodeRange_SortedNames() {
    var entity1 = BatteryEntity.builder().batteryId(123L).name("Exide")
        .postalCode(1001).wattCapacity(100).build();
    var entity2 = BatteryEntity.builder().batteryId(124L).name("Amaron")
        .postalCode(1001).wattCapacity(60).build();

    var  expectedSortedames = List.of("Amaron","Exide");

    when(batteryRepository
        .findAllByPostalCodeLessThanEqualAndPostalCodeGreaterThanEqual(
            1010,1001))
        .thenReturn(List.of(entity1, entity2));
    var actual = powerPlantService.getBatteriesByPostalCodeRange(1001,1010);
    assertThat(actual.getNames()).isEqualTo(expectedSortedames);

  }

  @Test
  void getBatteriesByPostalCodeRange_Statistics() {
    var entity1 = BatteryEntity.builder().batteryId(123L).name("Exide")
        .postalCode(1001).wattCapacity(100).build();
    var entity2 = BatteryEntity.builder().batteryId(124L).name("Amaron")
        .postalCode(1001).wattCapacity(60).build();

    Statistics expectedStatistics = Statistics.builder().total(160).averageWatts(80L).build();

    when(batteryRepository
        .findAllByPostalCodeLessThanEqualAndPostalCodeGreaterThanEqual(
            1010,1001))
        .thenReturn(List.of(entity1, entity2));
    var actual = powerPlantService.getBatteriesByPostalCodeRange(1001,1010);
    assertThat(actual.getStatistics()).isEqualTo(expectedStatistics);

  }

}
