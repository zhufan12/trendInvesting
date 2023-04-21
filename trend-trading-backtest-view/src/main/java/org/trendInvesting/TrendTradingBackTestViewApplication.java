package org.trendInvesting;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TrendTradingBackTestViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrendTradingBackTestViewApplication.class,args);
    }
}
