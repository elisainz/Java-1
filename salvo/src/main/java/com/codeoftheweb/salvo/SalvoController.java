package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping ("/api")

public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping ("/games")
    public List <Map<String,Object>> getGames() {
        return gameRepository.findAll()
                             .stream ()
                             .map(Game->makeGameDTO(Game))
                             .collect(Collectors.toList());
    }
    public Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", game.getId());
        dto.put("created", game.getGameTime().getTime());
        return dto;
    }




}