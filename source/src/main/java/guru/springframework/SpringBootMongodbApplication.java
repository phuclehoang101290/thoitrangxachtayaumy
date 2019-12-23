package guru.springframework;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.firewall.HttpFirewall;
//import org.springframework.security.web.firewall.StrictHttpFirewall;


@SpringBootApplication
@Configuration
public class SpringBootMongodbApplication {

	public static void main(String[] args) {
		Properties properties = new Properties();
        properties.setProperty("spring.resources.static-locations",
                          "classpath:/static/");
        SpringApplication app =
                new SpringApplication(SpringBootMongodbApplication.class);
        app.setDefaultProperties(properties);
        app.run(args);
	}
	
	
//	  @Bean public BCryptPasswordEncoder passwordEncoder() { BCryptPasswordEncoder
//	  bCryptPasswordEncoder = new BCryptPasswordEncoder(); return
//	  bCryptPasswordEncoder; }
	  
//	  @Bean public HttpFirewall looseHttpFirewall() { StrictHttpFirewall firewall =
//	  new StrictHttpFirewall(); firewall.setAllowedHttpMethods(Arrays.asList("GET",
//	  "POST", "get", "post")); firewall.setAllowSemicolon(true);
//	  firewall.setAllowUrlEncodedSlash(true); firewall.setAllowBackSlash(true);
//	  firewall.setAllowUrlEncodedPercent(true);
//	  firewall.setAllowUrlEncodedPeriod(true);
//	  firewall.setUnsafeAllowAnyHttpMethod(true); return firewall; }
	 
}
