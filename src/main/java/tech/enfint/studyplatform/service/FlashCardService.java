package tech.enfint.studyplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.enfint.studyplatform.dto.FlashCardRequestDTO;
import tech.enfint.studyplatform.dto.FlashCardResponseDTO;
import tech.enfint.studyplatform.persistence.FlashCardRepository;
import tech.enfint.studyplatform.persistence.entity.FlashCard;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlashCardService
{
    @Autowired
    private FlashCardRepository cardRepository;
    private final FlashCardMapper cardMapper;

    public FlashCardService(FlashCardMapper cardMapper) {
        this.cardMapper = cardMapper;
    }

    public FlashCardResponseDTO createCard(FlashCardRequestDTO cardRequestDTO)
    {
        FlashCard card = cardRepository.save(cardMapper.flashCardRequestDtoToFlashCard(cardRequestDTO));

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

    public List<FlashCardResponseDTO> getCardsByDeckID(UUID deckID)
    {
        List<FlashCard> allCards = (List<FlashCard>) cardRepository.findAll();

        return allCards.stream()
                .filter(card -> card.getDeck().getId() == deckID)
                .map(cardMapper::flashCardToFlashCardResponseDTO)
                .collect(Collectors.toList());
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
