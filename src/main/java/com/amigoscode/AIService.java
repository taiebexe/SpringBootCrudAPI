package com.amigoscode;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    private final ChatClient chatClient;

    public AIService(ChatClient.Builder Builder) {
        chatClient = Builder.build();
    }
    public String chat(String prompt) {
        return chatClient.prompt(prompt).call().content();

    }
}
