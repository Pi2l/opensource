package edu.m;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class KeycloakAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  @Value("${jwt.auth.converter.resource-id}")
  private String resourceId;

  @Override
  public AbstractAuthenticationToken convert(Jwt source) {
    return new JwtAuthenticationToken(
            source, Stream.concat(new JwtGrantedAuthoritiesConverter().convert(source).stream(),
                    extractResourceRoles(source).stream())
                    .collect(Collectors.toSet())
            );
  }

  private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt source) {
    var resourceAccess = new HashMap<>(source.getClaim("resource_access"));
    var clientId = (Map<String, List<String>>) resourceAccess.get(resourceId);
    if (clientId != null) {
      return clientId.get("roles").stream()
              .map(role -> new SimpleGrantedAuthority("ROLE_" + role.replace("-", "_")))
              .collect(Collectors.toSet());
    }
    return null;
  }
}
