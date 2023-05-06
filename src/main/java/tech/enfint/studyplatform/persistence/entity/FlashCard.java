package tech.enfint.studyplatform.persistence.entity;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.UUID;

@Entity
@Table(name="flashCard", schema = "public")
public class FlashCard
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "question", nullable = false, unique = true)
    private String question;
    @Column(name = "answer", nullable = false)
    private String answer;
    @Column(name = "status", nullable = false)
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

    public UUID getId() {
        return id;
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

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setStatus(LeitnerSystem status) {
        this.status = status;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
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
