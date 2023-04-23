package tech.enfint.studyplatform.dto;

import tech.enfint.studyplatform.persistence.entity.LeitnerSystem;

public class FlashCardResponseDTO
{
    private String question;
    private String answer;
    private LeitnerSystem importance;
    private DeckResponseDTO deck;

    public FlashCardResponseDTO(String question, String answer, LeitnerSystem importance, DeckResponseDTO deck) {
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

    public DeckResponseDTO getDeck() {
        return deck;
    }
}
