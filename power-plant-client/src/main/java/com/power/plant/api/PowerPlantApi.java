package com.power.plant.api;


import com.power.plant.model.Battery;
import com.power.plant.model.BatteryResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PowerPlantApi {

  @PostMapping("/batteries")
  List<Battery> createBatteries(@RequestBody List<Battery> battery);


  @GetMapping("/batteries/postalCode/start/{start}/end/{end}")
  BatteryResponse getBatteriesByPostalCodeRange(@PathVariable Integer start, @PathVariable Integer end);

}
