package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix="security.codeconnect.client")
public class ConsulDemoConfig {
    private String tokenURL;

    static {
        System.out.println("Llega aca o no?");
    }

    public String getTokenURL() {
        return tokenURL;
    }

    public void setTokenURL(String tokenURL) {
        this.tokenURL = tokenURL;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println(
                "********** TestConfig >> security.codeconnect.tokenURL: " + tokenURL);
    }
}
