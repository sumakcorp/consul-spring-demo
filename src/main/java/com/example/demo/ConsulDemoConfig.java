package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Class used to demo injecting properties from Consul directly to config class properties.
 */
@RefreshScope
@Configuration
@ConfigurationProperties(prefix="security.codeconnect.client")
public class ConsulDemoConfig {

    /**
     * Property injected by name that matches the hierarchy 'security.codeconnect.client'
     */
    private String tokenURL;

    static {
        System.out.println("Configuration class loaded");
    }

    public String getTokenURL() {
        return tokenURL;
    }

    public void setTokenURL(String tokenURL) {
        this.tokenURL = tokenURL;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("ConsulDemoConfig >> security.codeconnect.tokenURL: " + tokenURL);
    }
}
