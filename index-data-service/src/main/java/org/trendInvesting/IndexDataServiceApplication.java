package org.trendInvesting;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import org.trendInvesting.config.RedisCacheConfig;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
@Import(value = RedisCacheConfig.class)
public class IndexDataServiceApplication {

    private static int defaultPort = 8091;

    private static int port = 0;
    public static void main(String[] args) {

        if(null!=args && 0!=args.length) {
            for (String arg : args) {
                if(arg.startsWith("port=")) {
                    String strPort= StrUtil.subAfter(arg, "port=", true);
                    if(NumberUtil.isNumber(strPort)) {
                        port = Convert.toInt(strPort);
                    }
                }
            }
        }

        if(0==port) {
            Future<Integer> future = ThreadUtil.execAsync(() ->{
                int p = 0;
                System.out.printf("please in the 5 ms enter the port , suggest port number is %d ,if over the 5 ms will default use the port  %d %n",defaultPort,defaultPort);
                Scanner scanner = new Scanner(System.in);
                while(true) {
                    String strPort = scanner.nextLine();
                    if(!NumberUtil.isInteger(strPort)) {
                        System.err.println("the port just can accept number");
                        continue;
                    }
                    else {
                        p = Convert.toInt(strPort);
                        scanner.close();
                        break;
                    }
                }
                return p;
            });
            try{
                port = future.get(5, TimeUnit.SECONDS);
            }
            catch (InterruptedException | ExecutionException | TimeoutException e){
                port = defaultPort;
            }

            if(!NetUtil.isUsableLocalPort(port)) {
                System.err.printf("the service port %d is in used , can not start ", port );
                System.exit(1);
            }

        }
        new SpringApplicationBuilder(IndexDataServiceApplication.class).properties("server.port=" + port).run(args);
    }
}