package io.pipecrafts.guard.config;

import io.pipecrafts.guard.jwt.CustomTokenEnhancer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

  @Value("${jwt.signing-key}")
  private String jwtKey;

  private final AuthenticationManager authenticationManager;
  private final CustomTokenEnhancer customTokenEnhancer;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
      .withClient("client")
      .secret("secret")
      .authorizedGrantTypes("password", "refresh_token")
      .scopes("read");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    final List<TokenEnhancer> tokenEnhancers = List.of(customTokenEnhancer, jwtAccessTokenConverter());
    tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);

    endpoints.authenticationManager(authenticationManager)
      .tokenStore(tokenStore())
      .tokenEnhancer(tokenEnhancerChain);
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    final var converter = new JwtAccessTokenConverter();
    converter.setSigningKey(jwtKey);
    return converter;
  }
}
