package tech.enfint.studyplatform.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.enfint.studyplatform.dto.*;
import tech.enfint.studyplatform.persistence.DeckRepository;
import tech.enfint.studyplatform.persistence.entity.Deck;
import tech.enfint.studyplatform.persistence.entity.Deck_;
import tech.enfint.studyplatform.persistence.entity.OrderDirection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeckService {
    @Autowired
    private DeckRepository deckRepository;
    private final DeckMapper deckMapper;

    private Specification<Deck> nameLike(String name){
        return new Specification<Deck>() {
            @Override
            public Predicate toPredicate(Root<Deck> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(Deck_.NAME)),
                        name == null ? "%" : "%"+name.toLowerCase()+"%");
            }
        };
    }

    private Specification<Deck> descriptionLike(String description){
        return new Specification<Deck>() {
            @Override
            public Predicate toPredicate(Root<Deck> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(Deck_.DESCRIPTION)),
                        description == null ? "%" : "%"+description.toLowerCase()+"%");
            }
        };
    }

    private Specification<Deck> creationDateEqual(LocalDateTime creationDate){
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

    public DeckResponseDTO getDeckResponseDTOByID(UUID uuid)
    {
        if(deckRepository.existsById(uuid))
        {
            return deckMapper.deckToDeckResponseDto(deckRepository.findById(uuid).get());
        }

        return null;
    }

    public Deck getDeckByID(UUID uuid)
    {
        if(deckRepository.existsById(uuid))
        {
            return deckRepository.findById(uuid).get();
        }

        return null;
    }

    public List<DeckResponseDTO>  getAllDecksByFilter(DeckFilterDto deckFilterDto)
    {

        List<DeckResponseDTO> deckResponseDTOList = deckRepository.findAll(
                Specification.where(nameLike(deckFilterDto.getName()))
                        .and(descriptionLike(deckFilterDto.getDescription()))
                        .and(creationDateEqual(deckFilterDto.getCreationDate())))
                .stream()
                .map(deckMapper::deckToDeckResponseDto)
                .collect(Collectors.toList());

        if(deckFilterDto.getOrderBy() != null)
        {
            switch (deckFilterDto.getOrderBy()) {
                case NAME -> {
                    if (deckFilterDto.getOrderDirection().equals(OrderDirection.DESC)) {
                        deckResponseDTOList.sort(new DeckResponseDtoComparatorByName().reversed());
                    } else {
                        deckResponseDTOList.sort(new DeckResponseDtoComparatorByName());
                    }
                }
                case DESCRIPTION -> {
                    if (deckFilterDto.getOrderDirection().equals(OrderDirection.DESC)) {
                        deckResponseDTOList.sort(new DeckResponseDtoComparatorByDescription().reversed());
                    } else {
                        deckResponseDTOList.sort(new DeckResponseDtoComparatorByDescription());
                    }
                }
                case CREATION_DATE -> {
                    if (deckFilterDto.getOrderDirection().equals(OrderDirection.DESC)) {
                        deckResponseDTOList.sort(new DeckResponseDtoComparatorByCreationDate().reversed());
                    } else {
                        deckResponseDTOList.sort(new DeckResponseDtoComparatorByCreationDate());
                    }
                }
            }//switch (deckFilterDto.getOrderBy())
        }//if(deckFilterDto.getOrderBy() != null)

        return deckResponseDTOList;
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
