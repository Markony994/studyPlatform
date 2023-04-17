package tech.enfint.studyplatform.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.Set;

public class DeckGroup
{
    private String name;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Deck> decks;

    public DeckGroup() {
    }

    public DeckGroup(String name)
    {
        this.name = name;
    }

    public DeckGroup(String name, Set<Deck> decks) {
        this.name = name;
        this.decks = decks;
    }

    public String getName() {
        return name;
    }

    public Set<Deck> getDecks() {
        return decks;
    }
}
