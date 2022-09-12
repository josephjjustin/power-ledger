package com.power.plant.api;


import com.power.plant.model.Battery;
import com.power.plant.model.BatteryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Power Plant APIs")
public interface PowerPlantApi {

  String BATTERIES = "/batteries";
  String BATTERIES_POSTAL_CODE_START_START_END_END = "/batteries/postalCode/start/{start}/end/{end}";

  @PostMapping(BATTERIES)
  @Operation(summary = "Create batteries",
      description = "This API accepts list of batteries, each containing: name,postcode, and watt capacity.")
  @ApiResponse(
      responseCode = "200",
      description = "The response payload contains batteries saved.",
      content = @Content(schema = @Schema(implementation = Battery.class)))
  List<Battery> createBatteries(@RequestBody List<Battery> batteries);


  @GetMapping(BATTERIES_POSTAL_CODE_START_START_END_END)
  @Operation(summary = "Get batteries by postal code range",
      description = "This API  receives a postcode range.")
  @ApiResponse(
      responseCode = "200",
      description = "The response payload contains  list of names of batteries "
       + " that fall within the range, sorted alphabetically. statistics: total and average watt capacity.",
      content = @Content(schema = @Schema(implementation = BatteryResponse.class)))
  BatteryResponse getBatteriesByPostalCodeRange(@PathVariable Integer start, @PathVariable Integer end);

}
