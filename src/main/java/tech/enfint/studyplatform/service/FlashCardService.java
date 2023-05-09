package tech.enfint.studyplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.enfint.studyplatform.dto.*;
import tech.enfint.studyplatform.persistence.FlashCardRepository;
import tech.enfint.studyplatform.persistence.entity.FlashCard;
import tech.enfint.studyplatform.persistence.entity.OrderDirection;

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

    public List<FlashCardResponseDTO> getCardsByDeckID(UUID deckID, CardFilterDTO cardFilterDTO)
    {
        boolean _words = cardFilterDTO.getWords() != null
                && cardFilterDTO.getWords().get(0) != null;

        List<FlashCardResponseDTO> flashCardResponseDTOList =
                ((List<FlashCard>) cardRepository.findAll())
                .stream()
                .filter(card -> card.getDeck().getId().equals(deckID))
                .filter(card->
                        {
                            if(_words)
                            {
                                return cardFilterDTO.getWords().stream()
                                        .anyMatch(word->
                                                card.getAnswer().contains(word) ||
                                                card.getQuestion().contains(word));
                            }

                            return true;
                        })
                .map(cardMapper::flashCardToFlashCardResponseDTO)
                .collect(Collectors.toList());

        if(cardFilterDTO.getOrderBy() != null)
        {
            switch (cardFilterDTO.getOrderBy()) {
                case ANSWER -> {
                    if (cardFilterDTO.getOrderDirection().equals(OrderDirection.DESC)) {
                        flashCardResponseDTOList.sort(new FlashCardResponseDtoComparatorByAnswer().reversed());
                    } else {
                        flashCardResponseDTOList.sort(new FlashCardResponseDtoComparatorByAnswer());
                    }
                }
                case QUESTION -> {
                    if (cardFilterDTO.getOrderDirection().equals(OrderDirection.DESC)) {
                        flashCardResponseDTOList.sort(new FlashCardResponseDtoComparatorByQuestion().reversed());
                    } else {
                        flashCardResponseDTOList.sort(new FlashCardResponseDtoComparatorByQuestion());
                    }
                }
                case STATUS -> {
                    if (cardFilterDTO.getOrderDirection().equals(OrderDirection.DESC)) {
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
