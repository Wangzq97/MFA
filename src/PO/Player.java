package PO;

public class Player {
    private String club;

    private String id;

    private String name;

    private Location location;

    private double attackAbility;

    private double defenderAbility;

    private double score;

    public Player(String club, String id, String name, Location location, double attackAbility,double defenderAbility, double score){
        this.club=club;
        this.id=id;
        this.name=name;
        this.location=location;
        this.attackAbility=attackAbility;
        this.defenderAbility=defenderAbility;
        this.score=score;

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


}
