package com.paypal.bfs.test.bookingserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EntityScan( basePackages= {"com.paypal.bfs.test.bookingserv.api.model","com.paypal.bfs.test.bookingserv.dao"})
//@ComponentScan({ "com.paypal.bfs.test.bookingserv.dao","com.paypal.bfs.test.bookingserv.api.model" })
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

public class BookingServApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookingServApplication.class, args);
    }
}