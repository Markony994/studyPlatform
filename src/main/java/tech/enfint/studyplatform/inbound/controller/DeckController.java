package tech.enfint.studyplatform.inbound.controller;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import tech.enfint.studyplatform.dto.DeckRequestDTO;
import tech.enfint.studyplatform.dto.DeckResponseDTO;
import tech.enfint.studyplatform.dto.FlashCardRequestDTO;
import tech.enfint.studyplatform.dto.FlashCardResponseDTO;
import tech.enfint.studyplatform.exception.DeckException;
import tech.enfint.studyplatform.logging.FieldType;
import tech.enfint.studyplatform.logging.Logging;
import tech.enfint.studyplatform.service.DeckMapper;
import tech.enfint.studyplatform.service.DeckService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/decks")
public class DeckController
{
    private final DeckService deckService;
    private final DeckMapper deckMapper;

    public DeckController(DeckService deckService, DeckMapper deckMapper) {
        this.deckService = deckService;
        this.deckMapper = deckMapper;
    }

    @GetMapping(produces = "application/json")
    public List<DeckResponseDTO> getDecks(@RequestParam(name = "name", required = false) String name,
                                             @RequestParam(name = "description", required = false) String description,
                                             @RequestParam(name = "creationDate", required = false)
                                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime creationDate,
                                          @RequestParam(name = "orderBy", required = false) String orderBy,
                                          @RequestParam(name = "orderDirection", required = false) String orderDirection)
            throws DeckException
    {
        return null;
    }

    @GetMapping(path= "/{deckID}", produces = "application/json")
    public List<DeckResponseDTO> getDeck(@PathVariable(name = "deckID") UUID deckID)
            throws DeckException
    {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @PostMapping(produces = "application/json", consumes = "application/json")
    public DeckResponseDTO createDeck(@RequestBody @Valid DeckRequestDTO deckRequestDTO)
            throws DeckException, WebExchangeBindException
    {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @DeleteMapping(path = "/{deckID}", produces = "application/json", consumes = "application/json")
    public void deleteDeck(@PathVariable(name = "deckID") UUID deckID)
            throws DeckException, WebExchangeBindException
    {

    }

    @Logging(logTypes = {FieldType.ERROR})
    @PutMapping(path = "/{deckID}", produces = "application/json", consumes = "application/json")
    public void updateDeck(@RequestBody @Valid DeckRequestDTO deckRequestDTO)
            throws DeckException, WebExchangeBindException
    {

    }


    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = "{deckID}/cards/{cardID}", produces = "application/json", consumes = "application/json")
    public void updateDeckCard(@RequestBody @Valid FlashCardRequestDTO cardRequestDTO)
    {

    }

    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = "{deckID}/cards", produces = "application/json")
    public List<FlashCardResponseDTO> getDeckCards(@PathVariable(name = "deckID") UUID deckID,
                                                   @RequestParam(name = "words", required = false) List<String> words,
                                                   @RequestParam(name = "ordering", required = false) String ordering,
                                                   @RequestParam(name = "orderBy", required = false) String orderBy,
                                                   @RequestParam(name = "orderDirection", required = false) String orderDirection)
    {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @PostMapping(path = "{deckID}/cards", produces = "application/json", consumes = "application/json")
    public ResponseEntity<FlashCardResponseDTO> addCardToDeck(@PathVariable(name = "deckID") UUID deckID,
                                                              @RequestBody @Valid FlashCardRequestDTO cardRequestDTO)
            throws WebExchangeBindException
    {
        return null;
    }

    @DeleteMapping(path = "{deckID}/cards/{cardID}", produces = "application/json")
    public void deleteCardFromDeck(@PathVariable(name = "deckID") UUID deckID,
                                                         @PathVariable(name = "cardID") UUID cardID)
    {

    }

    @PutMapping(path = "{deckID}/updateCard/{cardID}", produces = "application/json")
    public void updateCardInDeck(@PathVariable(name = "deckID") UUID deckID,
                                   @PathVariable(name = "cardID") UUID cardID)
    {

    }

    @GetMapping(path = "{deckID}/startLearning", produces = "application/json")
    public void startLearning()
    {

    }

    

}//public class DeckController
