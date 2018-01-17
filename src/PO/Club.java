package PO;

import java.io.Serializable;
import java.util.ArrayList;

public class Club implements Serializable {

    private String name;

    private ArrayList<Player> players;

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayerList(){
        return players;
    }

}
