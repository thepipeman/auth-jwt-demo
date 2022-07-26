package io.pipecrafts.guard.jwt;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    final var token = new DefaultOAuth2AccessToken(accessToken);
    final Map<String, Object> extraInfo = new HashMap<>();
    extraInfo.put("allowedBoards", "VICTORY_LINER,GENESIS");
    extraInfo.put("allowedOrigins", "NCR");
    token.setAdditionalInformation(extraInfo);
    return token;
  }
}
