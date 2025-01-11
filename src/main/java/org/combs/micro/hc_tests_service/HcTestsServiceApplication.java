package org.combs.micro.hc_tests_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class HcTestsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcTestsServiceApplication.class, args);
	}

}
