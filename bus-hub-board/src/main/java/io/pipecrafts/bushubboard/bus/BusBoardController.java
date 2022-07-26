package io.pipecrafts.bushubboard.bus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bus")
public class BusBoardController {

  @GetMapping("/board")
  public List<Bus> readBusBoard(OAuth2Authentication authentication) {
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

    final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();

    log.info("Authentication {}", details);

    return board;
  }
}
