package com.paypal.user_service.client;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletFeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // Replace this with actual token generation or retrieval logic
            String token = "YOUR_JWT_TOKEN";
            requestTemplate.header("Authorization", "Bearer " + token);
        };
    }
}
