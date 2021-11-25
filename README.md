# Spring Boot + Consul integration
## Spring 2.4 and above configuration file processing
Spring Boot 2.4.0.M2 has just been released, and it brings with it some interesting changes 
to the way that application.properties and application.yml files are loaded.

Click here to review all details of new features around configuration files.
https://spring.io/blog/2020/08/14/config-file-processing-in-spring-boot-2-4

## Importing additional configuration files
Prior to version 2.4.0, Spring Boot allowed including additional configuration files using the spring.config.location and spring.config.additional-location properties, but they had certain limitations. For instance, they had to be defined before starting the application (as environment or system properties, or using command-line arguments) as they were used early in the process.

The latest updates added the capability to usethe spring.config.import property within the application.properties or application.yml file to easily include additional files. This property supports some interesting features:

- adding several files or directories
- the files can be loaded either from the classpath or from an external directory
- indicating if the startup process should fail if a file is not found, or if it's an optional file
- importing extensionless files

Read more https://www.baeldung.com/properties-with-spring

## Spring profiles best practices
Spring provides a mighty tool for grouping configuration properties into so-called profiles, allowing us to activate a bunch of configurations with a single profile parameter. Spring Boot builds on top of that by allowing us to configure and activate profiles externally.
Profiles are perfect for setting up our application for different environments, but they’re also tempting in other use cases.
Read on to learn how profiles work, what use cases they support and in which cases we should rather not use them.

Read more https://reflectoring.io/spring-boot-profiles/

## Spring boot configuration files   
The configuration files are defined as follow:
```
|-- src
    |--main
        |--resources
            - bootstrap.yml 
            - local.yml
            - dev.yml
            - qa.yml
            - uat.yml
            - prod.yml
            - consul-spring-demo.yml    
```

- **bootstrap.yml** _main spring configuration file._
- **local.yml** _local development spring specific properties. This file is loaded by default when the application is run._
- **dev.yml** _development environment spring specific properties._
- **qa.yml** _Quality assurance environment spring specific properties._
- **uat.yml** _User acceptance test environment spring specific properties._
- **prod.yml** _Production environment spring specific properties._
- **consul-spring-demo.yml** _file used for loading properties to Consul server using CLI. This is for demo purposes only, it is not recommended to keep environment properties in the source code repository_

> For any profile other than local (local development) the application expect the configuration properties be loaded from **Consul**.

## Run Spring boot application from maven command
If the below command is executed the Spring boot application will run with the default profile enabled as per the instructions given in the bootstrap.yml
```
# Local environment
spring:
  config:
    activate:
      on-profile: "default"
    import: classpath:local.yml`
```

This command will launch the application default profile defined
```
mvn spring-boot:run
```
## Active profile using maven command
```
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## Installing Consul with docker container
Please follow the instructions listed on https://learn.hashicorp.com/tutorials/consul/docker-container-agents
The only important sections are the ones below.
- Get the Docker image
- Configure and run a Consul server
- Discover the server IP address

>Once you follow the instructions above you should be able to launch Consul console from http://localhost:8500.

## Load configuration properties to Consul using CLI
Copy the configuration file **consul-spring-demo.yml** to your Consul agent using the following command.
```
docker cp ~/your/path/consul-spring-demo.yml badger:/tmp/
```

The configuration below defined in the  *bootstrap.yml* will be used to load the configuration properties into Consul.   
```
prefix: config  # Set the basic folder of the configuration.
defaultContext: oxchg-accounts  # Set the folder name of the application
data-key: properties  # The Key in Key/Values of Consul corresponds to the entire configuration file
```

Now let's get into our badger (Consul agent) and run the following command.
```
consul kv put config/oxchg-accounts/properties @/tmp/consul-spring-demo.yml
```

If the command is executed correctly your should be able to see the loaded properties in the Consul console or retrive them using the following command.
```
consul kv get config/oxchg-accounts/properties
```