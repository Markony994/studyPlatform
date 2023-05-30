package tech.enfint.studyplatform.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import tech.enfint.studyplatform.persistence.entity.Deck;
import tech.enfint.studyplatform.persistence.entity.Deck_;

import java.time.LocalDateTime;

public class DeckSpecifications
{

    public static Specification<Deck> nameLike(String name) {
        return new Specification<Deck>() {
            @Override
            public Predicate toPredicate(Root<Deck> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(Deck_.NAME)),
                        name == null ? "%" : "%" + name.toLowerCase() + "%");
            }
        };
    }

    public static Specification<Deck> descriptionLike(String description) {
        return new Specification<Deck>() {
            @Override
            public Predicate toPredicate(Root<Deck> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(Deck_.DESCRIPTION)),
                        description == null ? "%" : "%" + description.toLowerCase() + "%");
            }
        };
    }

    public static Specification<Deck> creationDateEqual(LocalDateTime creationDate) {
        return new Specification<Deck>() {
            @Override
            public Predicate toPredicate(Root<Deck> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Deck_.CREATION_DATE),
                        creationDate == null ? root.get(Deck_.CREATION_DATE) : creationDate);
            }
        };
    }

}
