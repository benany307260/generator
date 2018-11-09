package ${group}.${artifact};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ${artifact?cap_first}Application {

	public static void main(String[] args) {
		SpringApplication.run(${artifact?cap_first}Application.class, args);
	}
}
