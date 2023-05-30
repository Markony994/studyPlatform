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

    public FlashCard flashCardRequestDtoToFlashCard(FlashCardRequestDTO flashCardRequestDTO) {
        return new FlashCard(
                flashCardRequestDTO.getQuestion(),
                flashCardRequestDTO.getAnswer());
    }

    public FlashCardResponseDTO flashCardToFlashCardResponseDTO(FlashCard flashCard) {
        return FlashCardResponseDTO.builder()
                .id(flashCard.getId())
                .question(flashCard.getQuestion())
                .answer(flashCard.getAnswer())
                .status(flashCard.getStatus())
                .deck(deckMapper.deckToDeckResponseDto(flashCard.getDeck()))
                .build();

    }


}
