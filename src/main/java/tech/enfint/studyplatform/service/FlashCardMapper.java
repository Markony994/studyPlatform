package tech.enfint.studyplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.enfint.studyplatform.dto.FlashCardRequestDTO;
import tech.enfint.studyplatform.dto.FlashCardResponseDTO;
import tech.enfint.studyplatform.persistence.entity.FlashCard;

@Component
@RequiredArgsConstructor
public class FlashCardMapper {

    private final DeckMapper deckMapper;

    public FlashCardResponseDTO flashCardToFlashCardResponseDTO(FlashCard flashCard) {
        return FlashCardResponseDTO.builder()
                .question(flashCard.getQuestion())
                .answer(flashCard.getAnswer())
                .importance(flashCard.getImportance())
                .deck(deckMapper.deckToDeckResponseDto(flashCard.getDeck()))
                .build();

    }


}
