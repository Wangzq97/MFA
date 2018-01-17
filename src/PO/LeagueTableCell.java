package PO;

import java.io.Serializable;

public class LeagueTableCell implements Serializable {

    public LeagueTableCell(String clubName){
        this.clubName=clubName;
        hasPlayed=0;
        wins=0;
        draws=0;
        loses=0;
        goals=0;
        losts=0;
        score=0;
    }

    private String clubName;

    private int hasPlayed;          //已比赛轮数

    private int wins;               //胜

    private int draws;              //平

    private int loses;              //负

    private int goals;              //进球

    private int losts;              //失球

    private int score;              //积分

    public void change(int goal, int lost){
        hasPlayed++;
        goals+=goal;
        losts+=lost;

        if(goal>lost){
            wins++;
            score+=3;
        }else if (goal==lost){
            draws++;
            score+=1;
        }else{
            loses++;
        }

    }

    public String getClubName() {
        return clubName;
    }

    public int getHasPlayed() {
        return hasPlayed;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLoses() {
        return loses;
    }

    public int getGoals() {
        return goals;
    }

    public int getLosts() {
        return losts;
    }

    public int getScore() {
        return score;
    }
}
