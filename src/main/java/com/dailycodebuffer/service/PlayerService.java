package com.dailycodebuffer.service;

import com.dailycodebuffer.model.Player;
import com.dailycodebuffer.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> playerList=new ArrayList<>();
    AtomicInteger id=new AtomicInteger(0);

    public List<Player> findAll(){
        return playerList;
    }

    public Optional<Player> findOne(Integer id){
       return playerList.stream().
               filter(player -> player.Id()==id).findFirst();
    }

    public Player createPlayer(String playerName, Team team){
        Player player=new Player(playerName,id.incrementAndGet(),team);
        playerList.add(player);
        return player;
    }

    public Player deletePlayer(Integer id){
        Player player=playerList.stream().
                filter(play -> play.Id()==id).findFirst().orElseThrow(()->new IllegalArgumentException("Player not found with Id"+id));
        playerList.remove(player);
        return player;
    }

    public Player updatePlayer(Integer id,String name,Team team){
        Player updatedPlayer=new Player(name,id,team);
        Optional<Player> optionalPlayer= playerList.stream().
                filter(player -> player.Id()==id).findFirst();
        if(optionalPlayer.isPresent()){
            Player foundPlayer= optionalPlayer.get();
            int index= playerList.indexOf(foundPlayer);
            playerList.set(index,updatedPlayer);
        }else{
            throw new IllegalArgumentException("Invalid Player !!!");
        }
        return updatedPlayer;
    }

    @PostConstruct
    private void init(){
        playerList.add(new Player("Dhoni",id.incrementAndGet(),Team.CSK));
        playerList.add(new Player("Rohit",id.incrementAndGet(),Team.MI));
        playerList.add(new Player("Virat",id.incrementAndGet(),Team.RCB));
        playerList.add(new Player("Rahul",id.incrementAndGet(),Team.GT));

    }


}
