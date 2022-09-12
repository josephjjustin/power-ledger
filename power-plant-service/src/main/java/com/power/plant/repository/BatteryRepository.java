package com.power.plant.repository;

import com.power.plant.entity.BatteryEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BatteryRepository extends CrudRepository<BatteryEntity, Long> {

  List<BatteryEntity> findAllByPostalCodeLessThanEqualAndPostalCodeGreaterThanEqual( final Integer endPostalCode, final Integer startPostalCode);
}
