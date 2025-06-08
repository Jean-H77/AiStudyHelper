package org.john.edu.services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import org.john.edu.models.FlashCardResponse;

@AiService
public interface FlashCardAssistant {

    @SystemMessage("You are an education expert helping students prepare for exams.")
    @UserMessage(
            """
            Analyze the handwritten or typed notes shown in this image: {{it}}.
    
            Based on the notes and the subject they are about, generate a total of 2 to 9 high-quality flashcards.
    
            Flashcards should include:
            - A clear and concise question.
            - A correct and informative answer.
    
            Prioritize creating flashcards directly from the content of the notes—definitions, formulas, dates, theorems, vocabulary, key facts, etc.
    
            Then, add additional flashcards that expand on the same subject area to reinforce deeper understanding (e.g., if the notes are about algebra, include related principles or examples that are commonly taught in that topic).
    
            All flashcards must stay relevant to the material and subject reflected in the notes. Do not include unrelated content.
            
            Respond ONLY with a valid JSON array of flashcards containing "question" and "answer".
            """
    )
    FlashCardResponse generate(String imageUrl);
}
