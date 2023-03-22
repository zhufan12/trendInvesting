package org.trendInvesting;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ThirdPartyIndexDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdPartyIndexDataApplication.class, args);
    }
}
