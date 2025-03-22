package dev.thiagokoster.encrypt;

import dev.thiagokoster.encrypt.Configurations.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class EncryptApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptApplication.class, args);
	}

}
