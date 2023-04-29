package tech.enfint.studyplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.enfint.studyplatform.dto.DeckRequestDTO;
import tech.enfint.studyplatform.dto.DeckResponseDTO;
import tech.enfint.studyplatform.persistence.entity.Deck;

@Component
@RequiredArgsConstructor
public class DeckMapper {

    public Deck DeckRequestDtoToDeck(DeckRequestDTO deckRequestDTO) {
        return new Deck(
                deckRequestDTO.getName(),
                deckRequestDTO.getDescription()
        );
    }

    public DeckResponseDTO deckToDeckResponseDto(Deck deck) {

        return DeckResponseDTO.builder()
                .id(deck.getId())
                .name(deck.getName())
                .description(deck.getDescription())
                .creationDate(deck.getCreationDate())
                .build();
    }

}
