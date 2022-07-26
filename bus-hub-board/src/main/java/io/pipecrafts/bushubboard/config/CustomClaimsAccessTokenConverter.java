package io.pipecrafts.bushubboard.config;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomClaimsAccessTokenConverter extends JwtAccessTokenConverter {

  @Override
  public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
//    return super.extractAuthentication(map);
    final var auth = super.extractAuthentication(map);
    auth.setDetails(map);

    return auth;
  }
}
