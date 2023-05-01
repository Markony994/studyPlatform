package tech.enfint.studyplatform.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.enfint.studyplatform.persistence.entity.Deck;

import java.util.UUID;

@Repository
public interface DeckRepository extends CrudRepository<Deck, UUID> {
}
