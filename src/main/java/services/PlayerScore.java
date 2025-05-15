package services;

import entities.Player;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerScore {

    private Player player;

    private int set;

    private int game;

    private int point;

    public PlayerScore(Player player){
        this.player = player;
        this.set = 0;
        this.game = 0;
        this.point = 0;
    }

    public int upPoint(){
        return this.point++;
    }

    public int downPoint(){
        return this.point--;
    }

    public int upGame(){
        return this.game++;
    }

    public int upSet(){
        return this.set++;
    }

}
