package tech.enfint.studyplatform.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Deck
{
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<FlashCard> cards;

    public Deck()
    {

    }

    public Deck(String name, String description) {
        this.name = name;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.cards = new HashSet<>();
    }

    public Deck(UUID id, String name, String description, LocalDateTime creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.cards = new HashSet<>();
    }

    public UUID getId() {
        return id;
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

    @Override
    public String toString() {
        return "Deck{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", cards=" + cards +
                '}';
    }
}
