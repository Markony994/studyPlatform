package tech.enfint.studyplatform.service;

import tech.enfint.studyplatform.dto.DeckRequestDTO;
import tech.enfint.studyplatform.dto.DeckResponseDTO;
import tech.enfint.studyplatform.persistence.entity.Deck;

import java.util.stream.Collectors;

public class DeckMapper
{
    private final FlashCardMapper flashCardMapper;

    public DeckMapper(FlashCardMapper flashCardMapper) {
        this.flashCardMapper = flashCardMapper;
    }

    public Deck DeckRequestDtoToDeck(DeckRequestDTO deckRequestDTO)
    {
        return new Deck(
                deckRequestDTO.getName(),
                deckRequestDTO.getDescription()
        );
    }

    public DeckResponseDTO deckToDeckResponseDto(Deck deck)
    {
        return new DeckResponseDTO(
                deck.getName(),
                deck.getDescription(),
                deck.getCreationDate(),
                deck.getCards().stream()
                        .map(flashCardMapper::flashCardToFlashCardResponseDTO)
                        .collect(Collectors.toSet()),
                deck.getGroup()
                );
    }

}
