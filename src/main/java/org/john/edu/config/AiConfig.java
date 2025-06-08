package org.john.edu.config;

import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.request.json.JsonArraySchema;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.model.chat.request.json.JsonSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static dev.langchain4j.model.chat.request.ResponseFormatType.JSON;

@Configuration
public class AiConfig {

    @Bean
    @Deprecated
    ResponseFormat flashCardResponse() {
        var itemSchema = JsonObjectSchema.builder()
                .addStringProperty("question")
                .addStringProperty("answer")
                .required("question", "answer")
                .build();

        var arraySchema = JsonArraySchema.builder()
                .description("All flashcards")
                .items(itemSchema)
                .build();

        return ResponseFormat.builder()
                .type(JSON)
                .jsonSchema(JsonSchema.builder()
                        .name("FlashCard")
                        .rootElement(arraySchema)
                        .build())
                .build();
    }
}
