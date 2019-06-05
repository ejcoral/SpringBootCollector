package com.endava.jiracollector.JiraCollector;

import com.endava.jiracollector.JiraCollector.config.JiraConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(JiraConfig.class)
@SpringBootApplication
public class JiraCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiraCollectorApplication.class, args);
	}

}

