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
    public List<DeckResponseDTO> getDecks(DeckFilterDto deckFilterDto)
            throws DeckException {
        log.info("DeskFilterDto {} ", deckFilterDto);
        return new ArrayList<>();
    }

    @GetMapping(path = "/{deckID}", produces = "application/json")
    public List<DeckResponseDTO> getDeck(@PathVariable(name = "deckID") UUID deckID)
            throws DeckException {
        log.info("deckID {} ", deckID);
        return new ArrayList<>();
    }

    @Logging(logTypes = {FieldType.ERROR})
    @PostMapping(produces = "application/json", consumes = "application/json")
    public DeckResponseDTO createDeck(@RequestBody @Valid DeckRequestDTO deckRequestDTO)
            throws DeckException, WebExchangeBindException {

        log.info("deckRequestDTO {} ", deckRequestDTO);
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @DeleteMapping(path = "/{deckID}", produces = "application/json")
    public void deleteDeck(@PathVariable(name = "deckID") UUID deckID)
            throws DeckException, WebExchangeBindException {
        log.info("deckID {} ", deckID);
    }

    @Logging(logTypes = {FieldType.ERROR})
    @PutMapping(path = "/{deckID}", produces = "application/json", consumes = "application/json")
    public void updateDeck(@PathVariable(name = "deckID") UUID deckID,
                           @RequestBody @Valid DeckRequestDTO deckRequestDTO)
            throws DeckException, WebExchangeBindException {
        log.info("deckID {} ", deckID);
        log.info("deckRequestDTO {} ", deckRequestDTO);
    }

    @GetMapping(path = "{deckID}/startLearning", produces = "application/json")
    public void startLearning(@PathVariable(name = "deckID") UUID deckID) {
        log.info("deckID {} ", deckID);
    }

}//public class DeckController
