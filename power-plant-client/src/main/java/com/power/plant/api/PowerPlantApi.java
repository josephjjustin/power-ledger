package com.power.plant.api;


import com.power.plant.model.Battery;
import com.power.plant.model.BatteryResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PowerPlantApi {

  String BATTERIES = "/batteries";
  String BATTERIES_POSTAL_CODE_START_START_END_END = "/batteries/postalCode/start/{start}/end/{end}";

  @PostMapping(BATTERIES)
  List<Battery> createBatteries(@RequestBody List<Battery> batteries);


  @GetMapping(BATTERIES_POSTAL_CODE_START_START_END_END)
  BatteryResponse getBatteriesByPostalCodeRange(@PathVariable Integer start, @PathVariable Integer end);

}
