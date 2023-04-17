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
    public List<DeckResponseDTO> getAllDecks()
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
    @DeleteMapping(path = "delete/deck/{name}", produces = "application/json", consumes = "application/json")
    public void deleteDeck(@PathVariable(name = "name") String name)
            throws DeckException, WebExchangeBindException
    {

    }

    @Logging(logTypes = {FieldType.ERROR})
    @PutMapping(path = "update/deck/{name}", produces = "application/json", consumes = "application/json")
    public void updatePost(@PathVariable(name = "name") String name,
                           @RequestBody @Valid DeckRequestDTO deckRequestDTO)
            throws DeckException, WebExchangeBindException
    {

    }

    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = {"by{name}", "by{description}", "by{creationDate}"}, produces = "application/json")
    public List<DeckResponseDTO> filterDecks(@RequestParam(name = "name", required = false) String name,
                                             @RequestParam(name = "description", required = false) String description,
                                             @RequestParam(name = "creationDate", required = false)
                                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime creationDate)
            throws DeckException
    {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = {"order/deck/{name}/byName/{ordering}"}, produces = "application/json")
    public List<DeckResponseDTO> orderDecksByName(@PathVariable(name = "name") String name,
            @PathVariable(name = "ordering") String ordering)
            throws DeckException
    {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = {"order/deck/{name}/byDescription/{ordering}"}, produces = "application/json")
    public List<DeckResponseDTO> orderDecksByDescription(@PathVariable(name = "name") String name,
            @PathVariable(name = "ordering") String ordering)
            throws DeckException
    {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = {"order/deck/{name}/byCreationDate/{ordering}"}, produces = "application/json")
    public List<DeckResponseDTO> orderDecksByCreationDate(@PathVariable(name = "name") String name,
            @PathVariable(name = "ordering") String ordering)
            throws DeckException
    {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = "deckCards/{name}", produces = "application/json")
    public List<FlashCardResponseDTO> getDeckCards(@PathVariable(name = "name") String name)
    {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = "deckCards/{name}/filterByWords/{words}", produces = "application/json")
    public List<FlashCardResponseDTO> filterDeckCards(@PathVariable(name = "name") String name,
                                                      @PathVariable(name = "words") List<String> words)
    {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = "deckCards/{name}/orderByQuestions/{ordering}", produces = "application/json")
    public List<FlashCardResponseDTO> orderDeckCardsByQuestions(@PathVariable(name = "ordering") String ordering)
    {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = "deckCards/{name}/orderByAnswers/{ordering}", produces = "application/json")
    public List<FlashCardResponseDTO> orderDeckCardsByAnswers(@PathVariable(name = "ordering") String ordering)
    {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @PostMapping(path = "deckCards/{name}/addCard", produces = "application/json", consumes = "application/json")
    public ResponseEntity<FlashCardResponseDTO> addCardToDeck(@PathVariable(name = "name") String name,
                                                              @RequestBody @Valid FlashCardRequestDTO cardRequestDTO)
            throws WebExchangeBindException
    {
        return null;
    }

    @DeleteMapping(path = "deckCards/{name}/deleteCard/{id}", produces = "application/json")
    public void deleteCardFromDeck(@PathVariable(name = "name") String name,
                                                         @PathVariable(name = "id") UUID id)
    {

    }

    @PutMapping(path = "deckCards/{name}/updateCard/{id}", produces = "application/json")
    public void updateCardInDeck(@PathVariable(name = "name") String name,
                                   @PathVariable(name = "id") UUID id)
    {

    }

    @GetMapping(path = "startLearning/{name}", produces = "application/json")
    public void startLearning()
    {

    }



}//public class DeckController
