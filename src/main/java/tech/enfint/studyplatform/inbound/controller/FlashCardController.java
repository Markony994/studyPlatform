package tech.enfint.studyplatform.inbound.controller;

import org.springframework.web.bind.annotation.*;
import tech.enfint.studyplatform.dto.FlashCardResponseDTO;
import tech.enfint.studyplatform.logging.FieldType;
import tech.enfint.studyplatform.logging.Logging;
import tech.enfint.studyplatform.persistence.entity.LeitnerSystem;
import tech.enfint.studyplatform.service.FlashCardMapper;
import tech.enfint.studyplatform.service.FlashCardService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/flashCards")
public class FlashCardController
{
    private final FlashCardService cardService;
    private final FlashCardMapper cardMapper;

    public FlashCardController(FlashCardService cardService, FlashCardMapper cardMapper) {
        this.cardService = cardService;
        this.cardMapper = cardMapper;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = "filterByWords/{words}", produces = "application/json")
    public List<FlashCardResponseDTO> filterDeckCards( @PathVariable(name = "words") List<String> words)
    {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = "orderByQuestions/{ordering}", produces = "application/json")
    public List<FlashCardResponseDTO> orderDeckCardsByQuestions(@PathVariable(name = "ordering") String ordering)
    {
        return null;
    }

    @Logging(logTypes = {FieldType.ERROR})
    @GetMapping(path = "orderByAnswers/{ordering}", produces = "application/json")
    public List<FlashCardResponseDTO> orderDeckCardsByAnswers(@PathVariable(name = "ordering") String ordering)
    {
        return null;
    }

    @PutMapping(path = "update/{id}", produces = "application/json")
    public void updateCard(@PathVariable(name = "id") UUID id)
    {

    }

    @PutMapping(path = "update/{id}/importance/{importance}", produces = "application/json")
    public void updateImportance(@PathVariable(name = "id") UUID id,
                                 @PathVariable(name = "importance") LeitnerSystem importance)
    {

    }



}
