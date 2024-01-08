package com.dailycodebuffer.controller;

import com.dailycodebuffer.model.Player;
import com.dailycodebuffer.model.Team;
import com.dailycodebuffer.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService=playerService;
    }

    @QueryMapping
    public List<Player> findAll(){
        return playerService.findAll();
    }

    @QueryMapping
    public Optional<Player> findOne(@Argument Integer id){
        return playerService.findOne(id);
    }

    @MutationMapping
    public Player create(@Argument String name, @Argument Team team){
        return playerService.createPlayer(name,team);
    }

    @MutationMapping
    public Player update(@Argument Integer id,@Argument String  name, @Argument Team team){
        return playerService.updatePlayer(id, name, team);
    }

    @MutationMapping
    public Player delete(@Argument Integer id){
       return playerService.deletePlayer(id);
    }

}
