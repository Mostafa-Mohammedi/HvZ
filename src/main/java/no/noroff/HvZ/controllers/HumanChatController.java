package no.noroff.HvZ.controllers;


import no.noroff.HvZ.mappers.HumanChatMapper;
import no.noroff.HvZ.models.HumanChat;
import no.noroff.HvZ.models.dto.humanChat.HumanChatPostDTO;
import no.noroff.HvZ.models.dto.humanChat.HumanChatPutDTO;
import no.noroff.HvZ.services.humanChat.HumanChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin("*")
@RestController
@RequestMapping(path="api/v1/humanChat")
public class HumanChatController {
    private HumanChatService humanChatService;
    private HumanChatMapper humanChatMapper;

    public HumanChatController(HumanChatService humanChatService, HumanChatMapper humanChatMapper) {
        this.humanChatService = humanChatService;
        this.humanChatMapper = humanChatMapper;
    }

    @GetMapping()
    public ResponseEntity getAll(){
        return ResponseEntity.ok(humanChatMapper.chatToChatDTOList(humanChatService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        return ResponseEntity.ok(humanChatMapper.humanChatToHumanChatDTO(humanChatService.findById(id)));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody HumanChatPostDTO humanChatPostDTO) throws URISyntaxException {
        HumanChat chat = humanChatMapper.HumanChatPostDTOtoHumanChat(humanChatPostDTO);
        humanChatService.add(chat);
        URI uri =  new URI("api/v1/humanChat/" + chat.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody HumanChatPutDTO humanChatPutDTO, @PathVariable Integer id){
        if(id != humanChatPutDTO.getId()){
            return ResponseEntity.badRequest().build();
        }
        else {
            humanChatService.update(humanChatMapper.HumanChatPutDTOtoHumanChat(humanChatPutDTO));
            return ResponseEntity.noContent().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        humanChatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
