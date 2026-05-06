package pe.edu.unitru.bienestar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "pe.edu.unitru.bienestar")
@EnableJpaRepositories(basePackages = "pe.edu.unitru.bienestar")
public class BienestarApplication {

	public static void main(String[] args) {
		SpringApplication.run(BienestarApplication.class, args);
	}

}
