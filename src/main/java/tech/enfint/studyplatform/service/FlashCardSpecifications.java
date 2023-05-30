package tech.enfint.studyplatform.service;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import tech.enfint.studyplatform.persistence.entity.FlashCard;
import tech.enfint.studyplatform.persistence.entity.FlashCard_;
import tech.enfint.studyplatform.persistence.entity.OrderCardsBy;
import tech.enfint.studyplatform.persistence.entity.OrderDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FlashCardSpecifications {

    public static Specification<FlashCard> deckIdEqual(UUID deckID) {
        return new Specification<FlashCard>() {
            @Override
            public Predicate toPredicate(Root<FlashCard> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(FlashCard_.DECK).get("id"), deckID);
            }
        };
    }

    private static Specification<FlashCard> containsWordIn(String word, String attributeName){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(attributeName),
                "%"+word+"%");
    }

    public static Specification<FlashCard> containsWordsInQuestionOrAnswer(List<String> words, List<OrderCardsBy> orderBy,
                                                                           OrderDirection orderDirection) {
        return new Specification<FlashCard>() {
            @Override
            public Predicate toPredicate(Root<FlashCard> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder)
            {
                Specification<FlashCard> inAswer =  Specification.anyOf(words.stream().map(
                                word ->  containsWordIn(word, FlashCard_.ANSWER)
                        ).collect(Collectors.toList())
                );

                Specification<FlashCard> inQuestion =  Specification.anyOf(words.stream().map(
                                word ->  containsWordIn(word, FlashCard_.QUESTION)
                        ).collect(Collectors.toList())
                );

                List<Order> orders = new ArrayList<>();

                for (OrderCardsBy by : orderBy)
                {
                    if(orderDirection.equals(OrderDirection.ASC))
                    {
                        orders.add(criteriaBuilder.asc(root.get(by.toString().toLowerCase())));
                    }
                    else
                    {
                        orders.add(criteriaBuilder.desc((root.get(by.toString().toLowerCase()))));
                    }

                }

                query.orderBy(orders);

                return Specification.anyOf(List.of(inAswer, inQuestion)).toPredicate(root, query, criteriaBuilder);
            }
        };

    }

}
