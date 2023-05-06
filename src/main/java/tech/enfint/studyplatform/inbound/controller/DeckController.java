package tech.enfint.studyplatform.inbound.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import tech.enfint.studyplatform.dto.DeckFilterDto;
import tech.enfint.studyplatform.dto.DeckRequestDTO;
import tech.enfint.studyplatform.dto.DeckResponseDTO;
import tech.enfint.studyplatform.exception.DeckException;
import tech.enfint.studyplatform.logging.FieldType;
import tech.enfint.studyplatform.logging.Logging;
import tech.enfint.studyplatform.service.DeckService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/decks")
@RequiredArgsConstructor
@Slf4j
public class DeckController {
    private final DeckService deckService;

    @GetMapping(produces = "application/json")
    public List<DeckResponseDTO> getDecks(DeckFilterDto deckFilterDto)
            throws DeckException {

        return deckService.getAllDecks();
    }

    @GetMapping(path = "/{deckID}", produces = "application/json")
    public DeckResponseDTO getDeck(@PathVariable(name = "deckID") UUID deckID)
            throws DeckException {

        return deckService.getDeckByID(deckID);
    }

    @Logging(logTypes = {FieldType.ERROR})
    @PostMapping(produces = "application/json", consumes = "application/json")
    public DeckResponseDTO createDeck(@RequestBody @Valid DeckRequestDTO deckRequestDTO)
            throws DeckException, WebExchangeBindException {

        return deckService.createDeck(deckRequestDTO);
    }

    @Logging(logTypes = {FieldType.ERROR})
    @DeleteMapping(path = "/{deckID}", produces = "application/json")
    public void deleteDeck(@PathVariable(name = "deckID") UUID deckID)
            throws DeckException, WebExchangeBindException {

        deckService.deleteDeckByID(deckID);
    }

    @Logging(logTypes = {FieldType.ERROR})
    @PutMapping(path = "/{deckID}", produces = "application/json", consumes = "application/json")
    public void updateDeck(@PathVariable(name = "deckID") UUID deckID,
                           @RequestBody @Valid DeckRequestDTO deckRequestDTO)
            throws DeckException, WebExchangeBindException {

        deckService.updateDeck(deckRequestDTO, deckID);
    }

    @GetMapping(path = "{deckID}/startLearning", produces = "application/json")
    public void startLearning(@PathVariable(name = "deckID") UUID deckID) {
        log.info("deckID {} ", deckID);
    }

}//public class DeckController
