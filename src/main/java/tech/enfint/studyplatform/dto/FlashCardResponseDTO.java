package tech.enfint.studyplatform.dto;

import lombok.Builder;
import lombok.Value;
import tech.enfint.studyplatform.persistence.entity.LeitnerSystem;

import java.util.UUID;

@Value
@Builder
public class FlashCardResponseDTO {
    UUID id;
    String question;
    String answer;
    @Builder.Default
    LeitnerSystem status = LeitnerSystem.REPEAT;
    DeckResponseDTO deck;

}
