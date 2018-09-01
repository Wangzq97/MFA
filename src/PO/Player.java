package PO;

import java.io.Serializable;

public class Player implements Serializable {
    private String club;

    private String id;

    private String name;

    private Location location;

    private double attackAbility;

    private double defenderAbility;

    private int goal;

    private int defend;

    private double score;

    public Player(String club, String id, String name, Location location, double attackAbility,double defenderAbility, double score){
        this.club=club;
        this.id=id;
        this.name=name;
        this.location=location;
        this.attackAbility=attackAbility;
        this.defenderAbility=defenderAbility;
        this.score=score;
        this.goal=0;
        this.defend=0;

    }

    public String getClub(){
        return club;
    }

    public String getName(){
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public double getAttackAbility(){
        return attackAbility;
    }

    public double getDefenderAbility(){
        return defenderAbility;
    }

    public void addGoal(){goal++;}

    public void addDefend(){defend++;}

    public int getGoal(){return goal;}

    public int getDefend(){return defend;}



}
