package tech.enfint.studyplatform.dto;

import tech.enfint.studyplatform.persistence.entity.Deck;
import tech.enfint.studyplatform.persistence.entity.LeitnerSystem;

public class FlashCardResponseDTO
{
    private String question;
    private String answer;
    private LeitnerSystem importance;
    private Deck deck;

    public FlashCardResponseDTO(String question, String answer, LeitnerSystem importance, Deck deck) {
        this.question = question;
        this.answer = answer;
        this.importance = importance;
        this.deck = deck;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public LeitnerSystem getImportance() {
        return importance;
    }

    public Deck getDeck() {
        return deck;
    }
}
