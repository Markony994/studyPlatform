package tech.enfint.studyplatform.dto;

import tech.enfint.studyplatform.persistence.entity.LeitnerSystem;

public class FlashCardResponseDTO
{
    private String question;
    private String answer;
    private LeitnerSystem status;
    private DeckResponseDTO deck;

    public FlashCardResponseDTO(String question, String answer, LeitnerSystem status, DeckResponseDTO deck) {
        this.question = question;
        this.answer = answer;
        this.status = status;
        this.deck = deck;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public LeitnerSystem getStatus() {
        return status;
    }

    public DeckResponseDTO getDeck() {
        return deck;
    }
}
