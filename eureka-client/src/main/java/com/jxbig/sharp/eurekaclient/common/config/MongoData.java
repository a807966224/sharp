package com.jxbig.sharp.eurekaclient.common.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(MongodbProperties.class)
public class MongoData {

    @Autowired
    MongodbProperties config;

    @Bean
    public MongoClient mongoClient() {
        List<ServerAddress> addresses = new ArrayList<>();
        MongoCredential credential = MongoCredential.createCredential(
                config.getUsername(), config.getDatabase(), config.getPassword().toCharArray());
        addresses.add(new ServerAddress(config.getHost(), Integer.parseInt(config.getPort())));
        MongoClient mongoClient = new MongoClient(addresses, credential, MongoClientOptions.builder().build());
        return mongoClient;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), config.getDatabase());
        return mongoTemplate;
    }

}
