package PO;

import java.util.ArrayList;

public class Club {

    private String name;

    private ArrayList<Player> players;

    public String getName(){
        return name;
    }

    public ArrayList<Player> getPlayerList(){
        return players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
