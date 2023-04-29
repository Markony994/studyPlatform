package tech.enfint.studyplatform.persistence.entity;

import jakarta.persistence.*;

public class FlashCard
{
    private String question;
    private String answer;
    private LeitnerSystem status;
    @OneToOne(fetch = FetchType.EAGER)
    private Deck deck;

    public FlashCard()
    {
    }

    public FlashCard(String question, String answer, LeitnerSystem status)
    {
        this.question = question;
        this.answer = answer;
        this.status = status;
    }

    public FlashCard(String question, String answer)
    {
        this.question = question;
        this.answer = answer;
        this.status = LeitnerSystem.REPEAT;
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

    public Deck getDeck() {
        return deck;
    }

    @Override
    public String toString() {
        return "FlashCard{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", status=" + status +
                ", deck=" + deck +
                '}';
    }
}
