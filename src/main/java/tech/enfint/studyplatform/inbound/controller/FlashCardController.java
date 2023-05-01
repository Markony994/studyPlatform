package tech.enfint.studyplatform.inbound.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import tech.enfint.studyplatform.dto.CardFilterDTO;
import tech.enfint.studyplatform.dto.FlashCardRequestDTO;
import tech.enfint.studyplatform.dto.FlashCardResponseDTO;
import tech.enfint.studyplatform.logging.FieldType;
import tech.enfint.studyplatform.logging.Logging;
import tech.enfint.studyplatform.service.FlashCardMapper;
import tech.enfint.studyplatform.service.FlashCardService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/flashCards")
@RequiredArgsConstructor
@Slf4j
public class FlashCardController
{
    private final FlashCardService cardService;
    private final FlashCardMapper cardMapper;

    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = "{deckID}/cards", produces = "application/json")
    public List<FlashCardResponseDTO> getCards(@PathVariable(name = "deckID") UUID deckID,
                                               CardFilterDTO cardFilterDTO) {
        log.info("deckID {} ", deckID);
        log.info("CardFilterDTO {} ", cardFilterDTO);
        return new ArrayList<>();
    }

    @Logging(logTypes = {FieldType.ERROR})
    @PostMapping(path = "{deckID}/cards", produces = "application/json", consumes = "application/json")
    public ResponseEntity<FlashCardResponseDTO> addCardToDeck(@PathVariable(name = "deckID") UUID deckID,
                                                              @RequestBody @Valid FlashCardRequestDTO
                                                                      cardRequestDTO)
            throws WebExchangeBindException {
        log.info("deckID {} ", deckID);
        log.info("cardRequestDTO {} ", cardRequestDTO);
        return null;
    }

    @DeleteMapping(path = "{deckID}/cards/{cardID}", produces = "application/json")
    public void deleteCardFromDeck(@PathVariable(name = "deckID") UUID deckID,
                                   @PathVariable(name = "cardID") UUID cardID) {
        log.info("deckID {} ", deckID);
        log.info("cardID {} ", cardID);
    }

    @PutMapping(path = "{deckID}/cards/{cardID}", produces = "application/json")
    public void updateCardInDeck(@PathVariable(name = "deckID") UUID deckID,
                                 @PathVariable(name = "cardID") UUID cardID) {
        log.info("deckID {} ", deckID);
        log.info("cardID {} ", cardID);
    }

}
