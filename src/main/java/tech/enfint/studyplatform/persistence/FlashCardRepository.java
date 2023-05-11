package tech.enfint.studyplatform.persistence;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.enfint.studyplatform.persistence.entity.FlashCard;

import java.util.UUID;

@Repository
public interface FlashCardRepository extends CrudRepository<FlashCard, UUID>,
        JpaSpecificationExecutor<FlashCard>
{
}
