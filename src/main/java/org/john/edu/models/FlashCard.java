package org.john.edu.models;

import dev.langchain4j.model.output.structured.Description;

@Description("The flash card structure")
public record FlashCard(
        @Description("The flash card question") String question,
        @Description("The flash card answer") String answer) {
}
