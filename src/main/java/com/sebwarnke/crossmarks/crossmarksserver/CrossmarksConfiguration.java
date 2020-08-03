package com.sebwarnke.crossmarks.crossmarksserver;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "crossmarks.security")
public class CrossmarksConfiguration {
  private String jwtSecret;
}
