package io.pipecrafts.bushubboard.bus;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusBoardController {

  @GetMapping("/board")
  public List<Bus> readBusBoard() {
    final List<Bus> board = new ArrayList<>();
    board.add(Bus.builder()
      .type("First Class")
      .departureTime(LocalDate.now().atTime(20, 0))
      .origin("Cubao")
      .destination("Santiago")
      .build());
    board.add(Bus.builder()
      .type("Delux")
      .departureTime(LocalDate.now().atTime(20, 30))
      .origin("Cubao")
      .destination("Santiago")
      .build());

    return board;
  }
}
