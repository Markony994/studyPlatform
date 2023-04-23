package tech.enfint.studyplatform.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.enfint.studyplatform.persistence.entity.DeckGroup;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class DeckResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime creationdate;
    private Set<FlashCardResponseDTO> cards;
    private DeckGroup deckGroup;



}
