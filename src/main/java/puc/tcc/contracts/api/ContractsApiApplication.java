package puc.tcc.contracts.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = "puc.tcc.contracts.api")
@PropertySource(value = "classpath:application.yml")
@PropertySource(value = "classpath:application-${spring.profiles.active}.yml")
public class ContractsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContractsApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

}
