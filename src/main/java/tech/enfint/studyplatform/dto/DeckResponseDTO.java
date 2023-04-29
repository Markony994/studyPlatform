package tech.enfint.studyplatform.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Value
public class DeckResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
}
