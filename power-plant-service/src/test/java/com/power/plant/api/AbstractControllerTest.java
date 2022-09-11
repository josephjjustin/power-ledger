package com.power.plant.api;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.power.plant.PowerPlantServiceApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Slf4j
@TestInstance(Lifecycle.PER_CLASS)
public abstract class AbstractControllerTest extends PowerPlantServiceApplicationTests {

  ObjectMapper objectMapper;

  @BeforeEach
  void abstractControllerTest_BeforeEach() {

    objectMapper = Jackson2ObjectMapperBuilder.json()
        .findModulesViaServiceLoader(true)
        .modulesToInstall(
            new JavaTimeModule(),
            new Jdk8Module())
        .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .serializationInclusion(Include.ALWAYS)
        .build();
  }


}
