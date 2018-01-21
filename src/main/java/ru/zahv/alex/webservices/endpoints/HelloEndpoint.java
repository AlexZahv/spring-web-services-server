package ru.zahv.alex.webservices.endpoints;

import org.example.hello.GetHelloRequest;
import org.example.hello.GetHelloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.zahv.alex.webservices.services.HelloService;

@Endpoint
public class HelloEndpoint {
    private static final String NAMESPACE = "http://www.example.org/hello";

    private HelloService helloService;

    @Autowired
    public HelloEndpoint(HelloService helloService) {
        this.helloService = helloService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getHelloRequest")
    @ResponsePayload
    public GetHelloResponse getHello(
            @RequestPayload GetHelloRequest request) {
        GetHelloResponse response = new GetHelloResponse();
        response.setHello(helloService.generateHello(request.getName()));
        return response;
    }
}
