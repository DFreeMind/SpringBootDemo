package com.weduoo.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * Hello world!
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ApplicationContext ctx = SpringApplication.run(App.class, args);
        String[] names = ctx.getBeanDefinitionNames();
        Arrays.sort(names);
        for (String name : names) {
            System.out.println(name);
        }
    }
}
