package openwms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import openwms.OpenwmsApplication;

@SpringBootApplication
public class OpenwmsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OpenwmsApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(OpenwmsApplication.class, args);
	}

}
