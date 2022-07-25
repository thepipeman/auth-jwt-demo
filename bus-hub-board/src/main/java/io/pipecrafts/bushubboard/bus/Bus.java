package io.pipecrafts.bushubboard.bus;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class Bus {
  String type;
  LocalDateTime departureTime;
  String origin;
  String destination;
}
