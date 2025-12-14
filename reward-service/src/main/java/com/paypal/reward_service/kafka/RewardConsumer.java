package com.paypal.reward_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paypal.reward_service.entity.Reward;
import com.paypal.reward_service.entity.Transaction;
import com.paypal.reward_service.repository.RewardRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RewardConsumer {

    private final RewardRepository rewardRepository;
    private final ObjectMapper objectMapper;
    private final ObjectMapper mapper;

    public RewardConsumer(RewardRepository rewardRepository, ObjectMapper objectMapper, ObjectMapper mapper) {
        this.rewardRepository = rewardRepository;
        this.objectMapper = objectMapper;
        this.mapper = mapper;
        this.mapper.registerModule(new JavaTimeModule());
    }

    @KafkaListener(topics = "txn-initiated" , groupId = "reward-group")
    public void consumerTransaction(Transaction transaction){
        try{
            if(rewardRepository.existsByTransactionId(transaction.getId())){
                System.out.println("Reward Already exists for transaction" + transaction.getId());
                return;

            }
            Reward reward = new Reward();
            reward.getUserId(transaction.getId());
            reward.getPoints(transaction.getAmount()*100);
            reward.setSendAt(LocalDateTime.now());
            reward.setTransactionId(transaction.getId());
            rewardRepository.save(reward);
            System.out.println("Reward saved:" + reward);

        } catch (Exception e) {
            System.err.println("Failed to process transaction" + transaction.getId() + ":" + e.getMessage());
            throw e;
        }

    }
}
