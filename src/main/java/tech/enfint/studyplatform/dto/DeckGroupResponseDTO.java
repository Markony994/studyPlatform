package tech.enfint.studyplatform.dto;

import java.util.Set;

public class DeckGroupResponseDTO
{
    private String name;
    private Set<DeckResponseDTO> decks;

    public DeckGroupResponseDTO(String name, Set<DeckResponseDTO> decks) {
        this.name = name;
        this.decks = decks;
    }

    public String getName() {
        return name;
    }

    public Set<DeckResponseDTO> getDecks() {
        return decks;
    }
}
