package tech.enfint.studyplatform.dto;

import lombok.Builder;
import lombok.Value;
import tech.enfint.studyplatform.persistence.entity.OrderDirection;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class CardFilterDTO
{
    UUID deckID;
    List<String> words;
    String orderBy;
    @Builder.Default
    OrderDirection orderDirection = OrderDirection.DESC;

}
