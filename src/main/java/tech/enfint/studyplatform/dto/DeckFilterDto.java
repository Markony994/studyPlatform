package tech.enfint.studyplatform.dto;

import lombok.Builder;
import lombok.Value;
import tech.enfint.studyplatform.persistence.entity.OrderDecksBy;
import tech.enfint.studyplatform.persistence.entity.OrderDirection;

import java.time.LocalDateTime;

@Value
@Builder
public class DeckFilterDto {
    String name;
    String description;
    LocalDateTime creationDate;
    OrderDecksBy orderBy;
    @Builder.Default
    OrderDirection orderDirection = OrderDirection.DESC;

}