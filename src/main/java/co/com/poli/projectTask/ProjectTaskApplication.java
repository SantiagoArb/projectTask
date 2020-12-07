package co.com.poli.projectTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProjectTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectTaskApplication.class, args);
	}

}
