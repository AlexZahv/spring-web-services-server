package ru.zahv.alex.webservices.services;

import org.example.hello.Hello;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HelloService {

    private static final String HELLO_MSG_PATTERN = "Hello, dear %s";

    public Hello generateHello(String name) {
        Hello hello = new Hello();
        hello.setContent(String.format(HELLO_MSG_PATTERN, name));
        hello.setDate((new Date()).toString());

        return hello;
    }
}
