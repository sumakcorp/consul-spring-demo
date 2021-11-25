package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * Rest controller used to test the direct @value injection of property value coming from Consul.
 */
@RestController
public class DemoRestServiceController {

    @Value("${security.codeconnect.client.tokenURL}")
    private String tokenURL;

    @RequestMapping(method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE}, value = "/randomUUID")
    public String randonUUID() {
        return String.valueOf(UUID.randomUUID());
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("DemoRestServiceController >> security.codeconnect.tokenURL: " + tokenURL);
    }
}