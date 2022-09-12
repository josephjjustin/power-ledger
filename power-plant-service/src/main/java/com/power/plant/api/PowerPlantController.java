package com.power.plant.api;


import com.power.plant.model.Battery;
import com.power.plant.model.BatteryResponse;
import com.power.plant.service.PowerPlantService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PowerPlantController implements PowerPlantApi {

  private final PowerPlantService powerPlantService;


  @Override
  public List<Battery> createBatteries(List<Battery> batteryList) {
    return powerPlantService.addBatteries(batteryList);
  }


  @Override
  public BatteryResponse getBatteriesByPostalCodeRange(Integer start, Integer end) {
    return powerPlantService.getBatteriesByPostalCodeRange(start, end);
  }
}
