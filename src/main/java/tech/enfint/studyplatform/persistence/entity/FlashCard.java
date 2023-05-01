package tech.enfint.studyplatform.persistence.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class FlashCard
{
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;
    private String question;
    private String answer;
    private LeitnerSystem status;
    @OneToOne(fetch = FetchType.EAGER)
    private Deck deck;

    public FlashCard()
    {
    }

    public FlashCard(UUID id, String question, String answer, LeitnerSystem status, Deck deck) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.status = status;
        this.deck = deck;
    }

    public FlashCard(String question, String answer, LeitnerSystem status) {
        this.id = UUID.randomUUID();
        this.question = question;
        this.answer = answer;
        this.status = status;
    }

    public FlashCard(String question, String answer)
    {
        this.id = UUID.randomUUID();
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
