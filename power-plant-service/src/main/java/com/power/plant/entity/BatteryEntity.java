package com.power.plant.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "battery")
@Builder
public class BatteryEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long batteryId;

  private String name;

  private Integer postalCode;

  private Integer wattCapacity;

}
