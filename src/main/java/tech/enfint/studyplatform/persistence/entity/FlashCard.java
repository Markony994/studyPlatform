package tech.enfint.studyplatform.persistence.entity;

import jakarta.persistence.*;

@Entity
public class FlashCard
{
    private String question;
    private String answer;
    private LeitnerSystem importance;
    @OneToOne(fetch = FetchType.EAGER)
    private Deck deck;

    public FlashCard()
    {
    }

    public FlashCard(String question, String answer, LeitnerSystem importance)
    {
        this.question = question;
        this.answer = answer;
        this.importance = importance;
    }

    public FlashCard(String question, String answer)
    {
        this.question = question;
        this.answer = answer;
        this.importance = LeitnerSystem.ONE;
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

    @Override
    public String toString() {
        return "FlashCard{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", importance=" + importance +
                '}';
    }
}
