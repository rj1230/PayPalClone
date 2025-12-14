package com.paypal.user_service.client;

import com.paypal.user_service.client.WalletFeignConfig;
import com.paypal.user_service.dto.CreateWalletRequest;
import com.paypal.user_service.dto.WalletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "wallet-service",
        url = "http://localhost:8088", // remove "/api/v1/wallets" from URL
        configuration = WalletFeignConfig.class
)
public interface WalletClient {

    @PostMapping("/api/v1/wallets")
    WalletResponse createWallet(@RequestBody CreateWalletRequest request);
}
