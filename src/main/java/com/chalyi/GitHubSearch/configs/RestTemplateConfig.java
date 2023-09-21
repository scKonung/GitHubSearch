package com.chalyi.GitHubSearch.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebClient.Builder builder(){
        String baseUrl = "https://api.github.com";
        return WebClient.builder().baseUrl(baseUrl);
    }

    @Bean
    public WebClient client( WebClient.Builder builder){
        return builder.build();
    }
}
