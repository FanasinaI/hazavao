package com.example.demo.client.openai.model;

import lombok.Data;
import java.util.List;

@Data
public class OpenAiChatCompletionResponse {
    private List<Choice> choices;

    @Data
    public static class Choice {
        private Message message;
    }

    @Data
    public static class Message {
        private String content;
        private String role;
    }
}
