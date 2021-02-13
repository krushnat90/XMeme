package com.krushnat90.xmeme.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * @author Krishnakant Thakur
 *
 */
@Configuration
@ComponentScan("com.krushnat90.xmeme")
public class MemeAppConfig {

	@Value("${spring.data.mongodb.uri}")
	private String connectionURI;

	@Value("${DBName}")
	private String databaseName;

	@Value("${AllowedOrigins}")
	private String allowedOrigins;

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create(connectionURI);
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();

	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), databaseName);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins(allowedOrigins).allowedMethods("GET", "POST", "PUT",
						"OPTIONS", "DELETE", "PATCH");
			}
		};
	}
}
