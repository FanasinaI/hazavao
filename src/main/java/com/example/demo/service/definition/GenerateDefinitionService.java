package com.example.demo.service.definition;

import com.example.demo.client.openai.model.OpenAiChatCompletionResponse; // Importez la nouvelle classe
import com.example.demo.domain.model.Definition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GenerateDefinitionService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final WebClient client = WebClient.builder()
            .baseUrl("https://api.openai.com/v1/chat/completions" )
            .build();

    public Mono<Definition> generateDefinition(String teny) {

        String requestBody = """
            {
              "model": "gpt-3.5-turbo",
              "messages": [
                { "role": "system", "content": "Tu es un dictionnaire de malgache." },
                { "role": "user", "content": "Donne moi une définition courte du mot suivant en malgache : %s" }
              ]
            }
            """.formatted(teny);

        return client.post()
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(OpenAiChatCompletionResponse.class) // Changez le type de retour ici
                .map(response -> {
                    String definitionContent = "Définition non trouvée."; // Valeur par défaut
                    if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
                        definitionContent = response.getChoices().get(0).getMessage().getContent();
                    }
                    return Definition.builder()
                            .definition(definitionContent)
                            .build();
                });
    }
}
