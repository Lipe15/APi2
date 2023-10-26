package Felipe.API2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Api2Application {

	public static void main(String[] args) {

		SpringApplication.run(Api2Application.class, args);
	}

}
