package tech.enfint.studyplatform.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Deck
{
    private String name;
    private String description;
    private LocalDateTime creationDate;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<FlashCard> cards;
    @OneToOne
    private DeckGroup group;

    public Deck()
    {

    }

    public Deck(String name, String description) {
        this.name = name;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.cards = new HashSet<>();
    }

    public Deck(String name, String description, Set<FlashCard> cards) {
        this.name = name;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Set<FlashCard> getCards() {
        return cards;
    }

    public DeckGroup getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", cards=" + cards +
                '}';
    }
}
