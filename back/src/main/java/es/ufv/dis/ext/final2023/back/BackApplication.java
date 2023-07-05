package es.ufv.dis.ext.final2023.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) {
		// URL para probarlo: localhost:8081/swagger-ui/index.html
		SpringApplication.run(BackApplication.class, args);
	}

}
