package com.endava.jiracollector.JiraCollector.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jira")
public class JiraConfig {

    private String authenticationToken;
}
