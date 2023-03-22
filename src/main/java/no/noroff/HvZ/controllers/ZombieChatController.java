package no.noroff.HvZ.controllers;


import no.noroff.HvZ.mappers.ZombieChatMapper;
import no.noroff.HvZ.models.ZombieChat;
import no.noroff.HvZ.models.dto.zombieChat.ZombieChatPostDTO;
import no.noroff.HvZ.models.dto.zombieChat.ZombieChatPutDTO;
import no.noroff.HvZ.services.zombieChat.ZombieChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin("*")
@RestController
@RequestMapping(path="api/v1/zombieChat")
public class ZombieChatController {
    private ZombieChatService zombieChatService;
    private ZombieChatMapper zombieChatMapper;

    public ZombieChatController(ZombieChatService zombieChatService, ZombieChatMapper zombieChatMapper) {
        this.zombieChatService = zombieChatService;
        this.zombieChatMapper = zombieChatMapper;
    }

    @GetMapping()
    public ResponseEntity getAll(){
        return ResponseEntity.ok(zombieChatMapper.chatToChatDTOList(zombieChatService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        return ResponseEntity.ok(zombieChatMapper.zombieChatToZombieChatDTO(zombieChatService.findById(id)));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody ZombieChatPostDTO zombieChatPostDTO) throws URISyntaxException {
        ZombieChat chat = zombieChatMapper.ZombieChatPostDTOtoZombieChat(zombieChatPostDTO);
        zombieChatService.add(chat);
        URI uri =  new URI("api/v1/zombieChat/" + chat.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody ZombieChatPutDTO zombieChatPutDTO, @PathVariable Integer id){
        if(id != zombieChatPutDTO.getId()){
            return ResponseEntity.badRequest().build();
        }
        else {
            zombieChatService.update(zombieChatMapper.ZombieChatPutDTOtoZombieChat(zombieChatPutDTO));
            return ResponseEntity.noContent().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        zombieChatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
