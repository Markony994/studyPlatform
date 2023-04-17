package tech.enfint.studyplatform.service;

import tech.enfint.studyplatform.dto.DeckGroupRequestDTO;
import tech.enfint.studyplatform.dto.DeckGroupResponseDTO;
import tech.enfint.studyplatform.persistence.entity.DeckGroup;

import java.util.stream.Collectors;

public class DeckGroupMapper
{
    private final DeckMapper deckMapper;

    public DeckGroupMapper(DeckMapper deckMapper) {
        this.deckMapper = deckMapper;
    }

    public DeckGroup deckGroupRequestDtoToDeckGroup(DeckGroupRequestDTO deckGroupRequestDTO)
    {
        return new DeckGroup
                (
                        deckGroupRequestDTO.getName()
                );
    }

    public DeckGroupResponseDTO deckGroupToDeckGroupResponseDto(DeckGroup deckGroup)
    {

        return new DeckGroupResponseDTO
                (
                        deckGroup.getName(),
                        deckGroup.getDecks().stream()
                                .map(deckMapper::deckToDeckResponseDto)
                                .collect(Collectors.toSet())
                );

    }

}
