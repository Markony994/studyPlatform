package tech.enfint.studyplatform.persistence.entity;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Deck
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<FlashCard> cards;

    public Deck()
    {

    }

    public Deck(String name, String description) {
        this.id = UUID.randomUUID();
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Deck that)) {
            return false;
        }
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(name, that.name);
        return eb.isEquals();
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
