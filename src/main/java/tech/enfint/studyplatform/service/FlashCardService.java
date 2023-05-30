package tech.enfint.studyplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.enfint.studyplatform.dto.*;
import tech.enfint.studyplatform.persistence.FlashCardRepository;
import tech.enfint.studyplatform.persistence.entity.FlashCard;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlashCardService {
    @Autowired
    private FlashCardRepository cardRepository;
    private final FlashCardMapper cardMapper;
    private final DeckService deckService;

    public FlashCardService(FlashCardMapper cardMapper, DeckService deckService) {
        this.cardMapper = cardMapper;
        this.deckService = deckService;
    }

    public FlashCardResponseDTO createCard(FlashCardRequestDTO cardRequestDTO, UUID deckID) {
        FlashCard _card = cardMapper.flashCardRequestDtoToFlashCard(cardRequestDTO);
        _card.setDeck(deckService.getDeckByID(deckID));

        FlashCard card = cardRepository.save(_card);

        return cardMapper.flashCardToFlashCardResponseDTO(card);
    }

    public FlashCardResponseDTO getCardByID(UUID uuid) {
        if (cardRepository.existsById(uuid)) {
            return cardMapper.flashCardToFlashCardResponseDTO(cardRepository.findById(uuid).get());
        }

        return null;
    }

    public List<FlashCardResponseDTO> getCardsByDeckID(UUID deckID, CardFilterDTO cardFilterDTO) {
        boolean _words = cardFilterDTO.getWords() != null
                && cardFilterDTO.getWords().get(0) != null;

        List<FlashCard> flashCardList;

        if (_words) {
            flashCardList = cardRepository.findAll(
                    Specification.where(FlashCardSpecifications.deckIdEqual(deckID))
                            .and(FlashCardSpecifications.containsWordsInQuestionOrAnswer(cardFilterDTO.getWords(),
                                    List.of(cardFilterDTO.getOrderBy()),
                                            cardFilterDTO.getOrderDirection()
                                    )));
        } else {
            flashCardList = cardRepository.findAll(
                    Specification.where(FlashCardSpecifications.deckIdEqual(deckID))
            );
        }

        List<FlashCardResponseDTO> flashCardResponseDTOList =
                flashCardList
                        .stream().map(cardMapper::flashCardToFlashCardResponseDTO)
                        .collect(Collectors.toList());

        return flashCardResponseDTOList;
    }

    public List<FlashCardResponseDTO> getAllCards() {
        return ((List<FlashCard>) cardRepository.findAll())
                .stream()
                .map(cardMapper::flashCardToFlashCardResponseDTO)
                .collect(Collectors.toList());
    }

    public FlashCardResponseDTO updateCard(FlashCardRequestDTO cardRequestDTO, UUID uuid) {
        if (cardRepository.existsById(uuid)) {
            FlashCard card = cardRepository.findById(uuid).get();

            //answer check
            if (!card.getAnswer().equalsIgnoreCase(cardRequestDTO.getAnswer())) {
                card.setAnswer(cardRequestDTO.getAnswer());
            }

            //question check
            if (!card.getQuestion().equalsIgnoreCase(cardRequestDTO.getQuestion())) {
                card.setQuestion(cardRequestDTO.getQuestion());
            }

            return cardMapper.flashCardToFlashCardResponseDTO(cardRepository.save(card));

        }//if(cardRepository.existsById(uuid))

        return null;

    }//public DeckResponseDTO updateDeck(DeckRequestDTO deckRequestDTO, UUID uuid)

    public void deleteCardByID(UUID uuid) {
        cardRepository.deleteById(uuid);
    }

}
