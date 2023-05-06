package tech.enfint.studyplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.enfint.studyplatform.dto.DeckRequestDTO;
import tech.enfint.studyplatform.dto.DeckResponseDTO;
import tech.enfint.studyplatform.persistence.DeckRepository;
import tech.enfint.studyplatform.persistence.entity.Deck;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeckService {
    @Autowired
    private DeckRepository deckRepository;
    private final DeckMapper deckMapper;

    public DeckService() {
        this.deckMapper = new DeckMapper();
    }

    public DeckResponseDTO createDeck(DeckRequestDTO deckRequestDTO)
    {
        Deck _deck = deckRepository.save(
                deckMapper.DeckRequestDtoToDeck(deckRequestDTO)
        );

        return deckMapper.deckToDeckResponseDto(_deck);
    }

    public DeckResponseDTO getDeckByID(UUID uuid)
    {
        if(deckRepository.existsById(uuid))
        {
            return deckMapper.deckToDeckResponseDto(deckRepository.findById(uuid).get());
        }

        return null;
    }

    public List<DeckResponseDTO> getAllDecks()
    {
        return ((List<Deck>) deckRepository.findAll())
                .stream()
                .map(deckMapper::deckToDeckResponseDto)
                .collect(Collectors.toList());
    }

    public DeckResponseDTO updateDeck(DeckRequestDTO deckRequestDTO, UUID uuid)
    {
        if(deckRepository.existsById(uuid))
        {
            Deck deck = deckRepository.findById(uuid).get();

            //name check
            if(!deck.getName().equalsIgnoreCase(deckRequestDTO.getName()))
            {
                deck.setName(deckRequestDTO.getName());
            }

            //description check
            if(!deck.getDescription().equalsIgnoreCase(deckRequestDTO.getDescription()))
            {
                deck.setDescription(deckRequestDTO.getDescription());
            }

            return deckMapper.deckToDeckResponseDto(deckRepository.save(deck));

        }//if(deckRepository.existsById(uuid))

        return null;

    }//public DeckResponseDTO updateDeck(DeckRequestDTO deckRequestDTO, UUID uuid)

    public void deleteDeckByID(UUID uuid)
    {
        deckRepository.deleteById(uuid);
    }

}
