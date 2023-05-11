package tech.enfint.studyplatform.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.enfint.studyplatform.dto.*;
import tech.enfint.studyplatform.persistence.FlashCardRepository;
import tech.enfint.studyplatform.persistence.entity.FlashCard;
import tech.enfint.studyplatform.persistence.entity.FlashCard_;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlashCardService
{
    @Autowired
    private FlashCardRepository cardRepository;
    private final FlashCardMapper cardMapper;
    private final DeckService deckService;

    private Specification<FlashCard> deckIdEqual(UUID deckID){
        return new Specification<FlashCard>() {
            @Override
            public Predicate toPredicate(Root<FlashCard> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(FlashCard_.DECK).get("id"), deckID);
            }
        };
    }

    private Specification<FlashCard> containsWordInQuestion(String word){
        return new Specification<FlashCard>() {
            @Override
            public Predicate toPredicate(Root<FlashCard> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.like(root.get(FlashCard_.QUESTION),
                        "%"+word+"%");
            }
        };
    }

    private Specification<FlashCard> containsWordInAnswer(String word){
        return new Specification<FlashCard>() {
            @Override
            public Predicate toPredicate(Root<FlashCard> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.like(root.get(FlashCard_.ANSWER),
                        "%"+word+"%");
            }
        };
    }

    private Specification<FlashCard> containsWordsInQuestionOrAnswer(List<String> words){
        return new Specification<FlashCard>() {
            @Override
            public Predicate toPredicate(Root<FlashCard> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Specification<FlashCard> specification = new Specification<FlashCard>() {
                    @Override
                    public Predicate toPredicate(Root<FlashCard> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                        return null;
                    }
                };

                for(int i=0; i<words.size(); i++)
                {
                    if(i == 0)
                    {
                        specification = containsWordInAnswer(words.get(i))
                                .or(containsWordInQuestion(words.get(i)));
                    }
                    else
                    {
                        specification.or(containsWordInAnswer(words.get(i)))
                                .or(containsWordInQuestion(words.get(i)));
                    }
                }

                return  specification.toPredicate(root, query, criteriaBuilder);
            }
        };
    }

    public FlashCardService(FlashCardMapper cardMapper, DeckService deckService) {
        this.cardMapper = cardMapper;
        this.deckService = deckService;
    }

    public FlashCardResponseDTO createCard(FlashCardRequestDTO cardRequestDTO, UUID deckID)
    {
        FlashCard _card = cardMapper.flashCardRequestDtoToFlashCard(cardRequestDTO);
        _card.setDeck(deckService.getDeckByID(deckID));

        FlashCard card = cardRepository.save(_card);

        return cardMapper.flashCardToFlashCardResponseDTO(card);
    }

    public FlashCardResponseDTO getCardByID(UUID uuid)
    {
        if(cardRepository.existsById(uuid))
        {
            return cardMapper.flashCardToFlashCardResponseDTO(cardRepository.findById(uuid).get());
        }

        return null;
    }

    public List<FlashCardResponseDTO> getCardsByDeckID(UUID deckID, CardFilterDTO cardFilterDTO) {
        boolean _words = cardFilterDTO.getWords() != null
                && cardFilterDTO.getWords().get(0) != null;

        List<FlashCard> flashCardList;

                if(_words)
                {
                    flashCardList = cardRepository.findAll(
                            Specification.where(deckIdEqual(deckID))
                                    .and(containsWordsInQuestionOrAnswer(cardFilterDTO.getWords())));
                }
                else
                {
                    flashCardList = cardRepository.findAll(
                            Specification.where(deckIdEqual(deckID)));
                }

        List<FlashCardResponseDTO> flashCardResponseDTOList =
                flashCardList
                .stream().map(cardMapper::flashCardToFlashCardResponseDTO)
                .collect(Collectors.toList());

        if(cardFilterDTO.getOrderBy() != null)
        {
            switch (cardFilterDTO.getOrderBy()) {
                case ANSWER -> {
                    if (cardFilterDTO.getOrderDirection().equals(tech.enfint.studyplatform.persistence.entity.OrderDirection.DESC)) {
                        flashCardResponseDTOList.sort(new FlashCardResponseDtoComparatorByAnswer().reversed());
                    } else {
                        flashCardResponseDTOList.sort(new FlashCardResponseDtoComparatorByAnswer());
                    }
                }
                case QUESTION -> {
                    if (cardFilterDTO.getOrderDirection().equals(tech.enfint.studyplatform.persistence.entity.OrderDirection.DESC)) {
                        flashCardResponseDTOList.sort(new FlashCardResponseDtoComparatorByQuestion().reversed());
                    } else {
                        flashCardResponseDTOList.sort(new FlashCardResponseDtoComparatorByQuestion());
                    }
                }
                case STATUS -> {
                    if (cardFilterDTO.getOrderDirection().equals(tech.enfint.studyplatform.persistence.entity.OrderDirection.DESC)) {
                        flashCardResponseDTOList.sort(new FlashCardResponseDtoComparatorByStatus().reversed());
                    } else {
                        flashCardResponseDTOList.sort(new FlashCardResponseDtoComparatorByStatus());
                    }
                }
            }//switch (cardFilterDTO.getOrderBy())
        }//if(cardFilterDTO.getOrderBy() != null)

        return flashCardResponseDTOList;
    }

    public List<FlashCardResponseDTO> getAllCards()
    {
        return ((List<FlashCard>) cardRepository.findAll())
                .stream()
                .map(cardMapper::flashCardToFlashCardResponseDTO)
                .collect(Collectors.toList());
    }

    public FlashCardResponseDTO updateCard(FlashCardRequestDTO cardRequestDTO, UUID uuid)
    {
        if(cardRepository.existsById(uuid))
        {
            FlashCard card = cardRepository.findById(uuid).get();

            //answer check
            if(!card.getAnswer().equalsIgnoreCase(cardRequestDTO.getAnswer()))
            {
                card.setAnswer(cardRequestDTO.getAnswer());
            }

            //question check
            if(!card.getQuestion().equalsIgnoreCase(cardRequestDTO.getQuestion()))
            {
                card.setQuestion(cardRequestDTO.getQuestion());
            }

            return cardMapper.flashCardToFlashCardResponseDTO(cardRepository.save(card));

        }//if(cardRepository.existsById(uuid))

        return null;

    }//public DeckResponseDTO updateDeck(DeckRequestDTO deckRequestDTO, UUID uuid)

    public void deleteCardByID(UUID uuid)
    {
        cardRepository.deleteById(uuid);
    }

}
