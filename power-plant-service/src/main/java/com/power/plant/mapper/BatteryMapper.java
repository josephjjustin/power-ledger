package com.power.plant.mapper;

import com.power.plant.entity.BatteryEntity;
import com.power.plant.model.Battery;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BatteryMapper {

List<BatteryEntity> modelToEntity(List<Battery> list);
  List<Battery> entityToModel(Iterable<BatteryEntity> list);
}
