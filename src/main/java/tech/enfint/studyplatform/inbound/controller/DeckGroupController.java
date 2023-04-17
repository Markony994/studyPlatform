package tech.enfint.studyplatform.inbound.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.enfint.studyplatform.dto.DeckGroupRequestDTO;
import tech.enfint.studyplatform.dto.DeckGroupResponseDTO;
import tech.enfint.studyplatform.service.DeckGroupMapper;
import tech.enfint.studyplatform.service.DeckGroupService;

import java.util.List;

@RestController
@RequestMapping("/deckGroups")
public class DeckGroupController
{
    private final DeckGroupService deckGroupService;
    private final DeckGroupMapper deckGroupMapper;

    public DeckGroupController(DeckGroupService deckGroupService, DeckGroupMapper deckGroupMapper) {
        this.deckGroupService = deckGroupService;
        this.deckGroupMapper = deckGroupMapper;
    }

    @GetMapping(produces = "application/json")
    public List<DeckGroupResponseDTO> getAllGroups()
    {

        return null;

    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public DeckGroupResponseDTO createGroup(@RequestBody @Valid DeckGroupRequestDTO deckGroupRequestDTO)
    {
        return null;
    }

    @PutMapping(path = "{groupName}/addDeck/{deckName}", consumes = "application/json")
    public void addDeckToGroup(@PathVariable(name = "groupName") String groupName,
                               @PathVariable(name = "deckName") String deckName)
    {



    }

    @PutMapping(path = "{groupName}/removeDeck/{deckName}", consumes = "application/json")
    public void removeDeckFromGroup(@PathVariable(name = "groupName") String groupName,
                                    @PathVariable(name = "deckName") String deckName)
    {



    }

}
