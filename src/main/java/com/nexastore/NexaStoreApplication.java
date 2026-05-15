package com.nexastore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NexaStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(NexaStoreApplication.class, args);
        System.out.println("--------NexaStore running--------");
        System.out.println("link : "+"http://localhost:8080");
        System.out.println("-----------click above-----------");



    }
}
