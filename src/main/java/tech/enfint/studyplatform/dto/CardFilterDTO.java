package tech.enfint.studyplatform.dto;

import lombok.Builder;
import lombok.Value;
import tech.enfint.studyplatform.persistence.entity.OrderCardsBy;
import tech.enfint.studyplatform.persistence.entity.OrderDirection;

import java.util.List;

@Value
@Builder
public class CardFilterDTO
{
    List<String> words;
    OrderCardsBy orderBy;
    @Builder.Default
    OrderDirection orderDirection = OrderDirection.DESC;

}
