package org.combs.micro.hc_tests_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
//@EnableWebSecurity
public class HcTestsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcTestsServiceApplication.class, args);
	}
	/*@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(AbstractHttpConfigurer::disable)
				.httpBasic(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
				.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()))
				.build();
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:8082") // адрес вашего шлюза
						.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}*/


}
