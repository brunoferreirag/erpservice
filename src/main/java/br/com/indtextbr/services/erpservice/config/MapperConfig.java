package br.com.indtextbr.services.erpservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class MapperConfig {
	@Bean
	public ObjectMapper getObjectMapper() {
		 return new ObjectMapper()
			        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			        .setPropertyNamingStrategy(SnakeCaseStrategy.SNAKE_CASE)
			        .findAndRegisterModules()
			        .registerModule(new JavaTimeModule());
	}
}
