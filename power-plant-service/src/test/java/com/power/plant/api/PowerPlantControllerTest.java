package com.power.plant.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.power.plant.model.Battery;
import com.power.plant.model.BatteryResponse;
import com.power.plant.model.BatteryResponse.Statistics;
import com.power.plant.service.PowerPlantService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


public class PowerPlantControllerTest extends AbstractControllerTest {

  MockMvc mockMvc;

  @MockBean
  PowerPlantService powerPlantService;

  @Autowired
 WebApplicationContext wac;

  @BeforeAll
  void beforeEach() {

    this.mockMvc = MockMvcBuilders
        .webAppContextSetup(this.wac)
        .build();
  }

  @Test
  void createBatteries_shouldReturnHttpStatus200() throws Exception {
    List<Battery> listOfBatteries = getBatteries();
    when(powerPlantService.addBatteries(listOfBatteries)).thenReturn(listOfBatteries);

    mockMvc.perform(
            post(PowerPlantApi.BATTERIES)
                .content(asJsonString(listOfBatteries))
                .characterEncoding(StandardCharsets.UTF_8.displayName())
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void getBatteriesByPostalCodeRange_successfully() throws Exception {
    BatteryResponse batteryResponse = getBatteryResponse();
    when(powerPlantService.getBatteriesByPostalCodeRange(1, 2)).thenReturn(batteryResponse);

    mockMvc.perform(
            get(PowerPlantApi.BATTERIES_POSTAL_CODE_START_START_END_END,1,2)
                .characterEncoding(StandardCharsets.UTF_8.displayName())
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  private List<Battery> getBatteries() {
    return List.of(Battery.builder().name("Exide").postalCode(1001).wattCapacity(100).build(),
        Battery.builder().name("Toshiba").postalCode(1001).wattCapacity(120).build());
  }

  private BatteryResponse getBatteryResponse() {
    return BatteryResponse.builder().names(List.of("Amaron","Exide")).statistics(
        Statistics.builder().averageWatts(80).total(160).build()).build();
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
