package tech.enfint.studyplatform.dto;

import lombok.Builder;
import lombok.Value;
import tech.enfint.studyplatform.persistence.entity.LeitnerSystem;

@Value
@Builder
public class FlashCardResponseDTO
{
    String question;
    String answer;
    @Builder.Default
    LeitnerSystem status = LeitnerSystem.REPEAT;
    DeckResponseDTO deck;

}
