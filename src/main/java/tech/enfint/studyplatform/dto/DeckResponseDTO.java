package tech.enfint.studyplatform.dto;

import tech.enfint.studyplatform.persistence.entity.DeckGroup;

import java.time.LocalDateTime;
import java.util.Set;

public class DeckResponseDTO
{
    private String name;
    private String description;
    private LocalDateTime creationdate;
    private Set<FlashCardResponseDTO> cards;
    private DeckGroup deckGroup;

    public DeckResponseDTO(String name, String description, LocalDateTime creationdate,
                           Set<FlashCardResponseDTO> cards, DeckGroup deckGroup) {
        this.name = name;
        this.description = description;
        this.creationdate = creationdate;
        this.cards = cards;
        this.deckGroup = deckGroup;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreationdate() {
        return creationdate;
    }

    public Set<FlashCardResponseDTO> getCards() {
        return cards;
    }

    public DeckGroup getDeckGroup() {
        return deckGroup;
    }
}
