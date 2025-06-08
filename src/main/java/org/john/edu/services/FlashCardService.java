package org.john.edu.services;

import lombok.RequiredArgsConstructor;
import org.john.edu.models.FlashCard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlashCardService {

    private final FlashCardAssistant flashCardAssistant;

    public List<FlashCard> generateFlashcards(String imageUrl) {
        var response = flashCardAssistant.generate(imageUrl);

        assert response != null;
        var flashCards = response.flashCards();

        if(flashCards.size() > 10)
            flashCards = flashCards.subList(0, 10);

        return flashCards;
    }
}
