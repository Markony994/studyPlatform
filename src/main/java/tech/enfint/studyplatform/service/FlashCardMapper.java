package tech.enfint.studyplatform.service;

import tech.enfint.studyplatform.dto.FlashCardRequestDTO;
import tech.enfint.studyplatform.dto.FlashCardResponseDTO;
import tech.enfint.studyplatform.persistence.entity.FlashCard;

public class FlashCardMapper
{

    private final DeckMapper deckMapper;

    public FlashCardMapper(DeckMapper deckMapper) {
        this.deckMapper = deckMapper;
    }

    public FlashCard FLashCardRequestDTOToFlashCard(FlashCardRequestDTO flashCardRequestDTO)
    {

        return new FlashCard(flashCardRequestDTO.getQuestion(), flashCardRequestDTO.getQuestion());

    }

    public FlashCardResponseDTO flashCardToFlashCardResponseDTO(FlashCard flashCard)
    {
        return new FlashCardResponseDTO(
                flashCard.getQuestion(),
                flashCard.getAnswer(),
                flashCard.getImportance(),
                deckMapper.deckToDeckResponseDto(flashCard.getDeck()));
    }



}
