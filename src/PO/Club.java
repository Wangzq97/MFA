package PO;

import java.util.ArrayList;

public class Club implements Cloneable{

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

    @Override
    public Object clone() {
        Club club = null;
        try{
            club = (Club)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return club;
    }

}
