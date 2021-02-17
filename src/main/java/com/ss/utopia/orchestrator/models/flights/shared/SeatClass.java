package com.ss.utopia.orchestrator.models.flights.shared;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.ss.utopia.orchestrator.exception.InvalidEnumValue;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SeatClass {
  ECONOMY,
  BUSINESS;

  private static final List<String> stringValues = Arrays.stream(SeatClass.values())
      .map(Enum::toString)
      .collect(Collectors.toList());

  @JsonCreator
  public static SeatClass fromString(String text) {
    try {
      return SeatClass.valueOf(text.toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new InvalidEnumValue(text, stringValues);
    }
  }
}
