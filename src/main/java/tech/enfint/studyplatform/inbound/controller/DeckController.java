package tech.enfint.studyplatform.inbound.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import tech.enfint.studyplatform.dto.*;
import tech.enfint.studyplatform.exception.DeckException;
import tech.enfint.studyplatform.logging.FieldType;
import tech.enfint.studyplatform.logging.Logging;
import tech.enfint.studyplatform.service.DeckMapper;
import tech.enfint.studyplatform.service.DeckService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/decks")
@RequiredArgsConstructor
@Slf4j
public class DeckController {
    private final DeckService deckService;
    private final DeckMapper deckMapper;

    @GetMapping(produces = "application/json")
    public List<DeckResponseDTO> getDecks(DeskFilterDto deskFilterDto)
            throws DeckException {
        log.info("DeskFilterDto {} ", deskFilterDto);
        return new ArrayList<>();
    }

    @GetMapping(path = "/{deckID}", produces = "application/json")
    public List<DeckResponseDTO> getDeck(@PathVariable(name = "deckID") UUID deckID)
            throws DeckException {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @PostMapping(produces = "application/json", consumes = "application/json")
    public DeckResponseDTO createDeck(@RequestBody @Valid DeckRequestDTO deckRequestDTO)
            throws DeckException, WebExchangeBindException {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @DeleteMapping(path = "/{deckID}", produces = "application/json", consumes = "application/json")
    public void deleteDeck(@PathVariable(name = "deckID") UUID deckID)
            throws DeckException, WebExchangeBindException {

    }

    @Logging(logTypes = {FieldType.ERROR})
    @PutMapping(path = "/{deckID}", produces = "application/json", consumes = "application/json")
    public void updateDeck(@RequestBody @Valid DeckRequestDTO deckRequestDTO)
            throws DeckException, WebExchangeBindException {

    }

    //TODO fix with argument resolver (create Dto, ....
    //TODO move to flash card controller
    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = "{deckID}/cards", produces = "application/json")
    public List<FlashCardResponseDTO> getDeckCards(@PathVariable(name = "deckID") UUID deckID,
                                                   @RequestParam(name = "words", required = false) List<String> words,
                                                   @RequestParam(name = "ordering", required = false) String ordering,
                                                   @RequestParam(name = "orderBy", required = false) String orderBy,
                                                   @RequestParam(name = "orderDirection", required = false) String orderDirection) {
        return null;
    }

    //TODO move to flash card controller
    @Logging(logTypes = {FieldType.ERROR})
    @PostMapping(path = "{deckID}/cards", produces = "application/json", consumes = "application/json")
    public ResponseEntity<FlashCardResponseDTO> addCardToDeck(@PathVariable(name = "deckID") UUID deckID,
                                                              @RequestBody @Valid FlashCardRequestDTO cardRequestDTO)
            throws WebExchangeBindException {
        return null;
    }

    //TODO move to flash card controller
    @DeleteMapping(path = "{deckID}/cards/{cardID}", produces = "application/json")
    public void deleteCardFromDeck(@PathVariable(name = "deckID") UUID deckID,
                                   @PathVariable(name = "cardID") UUID cardID) {

    }

    //TODO move to flash card controller
    @PutMapping(path = "{deckID}/updateCard/{cardID}", produces = "application/json")
    public void updateCardInDeck(@PathVariable(name = "deckID") UUID deckID,
                                 @PathVariable(name = "cardID") UUID cardID) {

    }

    @GetMapping(path = "{deckID}/startLearning", produces = "application/json")
    public void startLearning() {

    }


}//public class DeckController
