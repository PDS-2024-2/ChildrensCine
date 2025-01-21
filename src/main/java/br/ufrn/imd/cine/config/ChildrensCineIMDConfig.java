package br.ufrn.imd.cine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ChildrensCineIMDConfig {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
