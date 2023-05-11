package tech.enfint.studyplatform.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tech.enfint.studyplatform.persistence.entity.Deck;

import java.util.UUID;

@Repository
public interface DeckRepository extends JpaRepository<Deck, UUID>,
        JpaSpecificationExecutor<Deck> {
}
