package tech.enfint.studyplatform.dto;

import lombok.Builder;
import lombok.Value;
import tech.enfint.studyplatform.persistence.entity.OrderDirection;

import java.time.LocalDateTime;

@Value
@Builder
public class DeckFilterDto {

    String name;
    String description;
    @Builder.Default
    LocalDateTime creationDate = LocalDateTime.now();
    String orderBy;
    @Builder.Default
    OrderDirection orderDirection = OrderDirection.DESC;

}