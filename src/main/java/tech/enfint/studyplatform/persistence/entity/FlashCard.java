package tech.enfint.studyplatform.persistence.entity;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.UUID;

@Entity
public class FlashCard
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String question;
    @Column(nullable = false)
    private String answer;
    @Column(nullable = false)
    private LeitnerSystem status;
    @OneToOne(fetch = FetchType.LAZY)
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlashCard that)) {
            return false;
        }

        EqualsBuilder eb = new EqualsBuilder();
        eb.append(question, that.question);
        eb.append(deck, that.deck);
        return eb.isEquals();
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
