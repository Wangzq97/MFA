package PO.Games;

import PO.Club;
import PO.Player;

import java.util.ArrayList;
import java.util.Random;

public class Penalty {
    private Club club1;

    private Club club2;

    private int penalty1=0;
    private int penalty2=0;

    private GameResult gameResult;


    public Penalty(Club club1, Club club2,GameType gameType,int season, int time, int turn){
        gameResult=new GameResult("", gameType, season, time, turn, club1, club2, false, false);
        this.club1=club1;
        this.club2=club2;
    }

    public String getGameResultProgress(){
        return gameResult.getGameProcess();
    }



    public Club getWinner() {
        System.out.println("Start");
        for (int i = 0; i < 5; i++) {
            if (getPenalty(club1.getPlayerList().get(i % 3).getAttackAbility(), club2.getPlayerList().get(5).getDefenderAbility() / 2)) {
                gameResult.penalty(1);
                penalty1++;
                gameResult.addProcess(club1.getPlayerList().get(i % 3).getName() + "稳稳将球罚进");
            } else {
                gameResult.addProcess(club1.getPlayerList().get(i % 3).getName() + "竟然把球罚丢了");
            }

            if ((penalty1 - penalty2) > (5 - i) || (penalty2 - penalty1) > (4 - i)) {
                gameResult.addProcess("（点球） " + club1.getName() + " " + penalty1 + " ： " + penalty2 + " " + club2.getName() + " （点球）");
                if(penalty1>penalty2){
                    return club1;
                }else {
                    return club2;
                }
            }

            if (getPenalty(club2.getPlayerList().get(i % 3).getAttackAbility(), club1.getPlayerList().get(5).getDefenderAbility() / 2)) {
                gameResult.penalty(2);
                penalty2++;
                gameResult.addProcess(club2.getPlayerList().get(i % 3).getName() + "稳稳将球罚进");
            } else {
                gameResult.addProcess(club2.getPlayerList().get(i % 3).getName() + "竟然把球罚丢了");
            }

            if ((penalty1 - penalty2) > (4 - i) || (penalty2 - penalty1) > (4 - i)) {
                gameResult.addProcess("（点球） " + club1.getName() + " " + penalty1 + " ： " + penalty2 + " " + club2.getName() + " （点球）");
                if(penalty1>penalty2){
                    return club1;
                }else {
                    return club2;
                }
            }
        }
        int index=2;
        while (penalty1 == penalty2) {
            if (getPenalty(club1.getPlayerList().get(index).getAttackAbility(), club2.getPlayerList().get(5).getDefenderAbility() / 2)) {
                gameResult.penalty(1);
                penalty1++;
                gameResult.addProcess(club1.getPlayerList().get(index).getName() + "稳稳将球罚进");
            } else {
                gameResult.addProcess(club1.getPlayerList().get(index).getName() + "竟然把球罚丢了");
            }
            if (getPenalty(club2.getPlayerList().get(index).getAttackAbility(), club1.getPlayerList().get(5).getDefenderAbility() / 2)) {
                gameResult.penalty(2);
                penalty2++;
                gameResult.addProcess(club2.getPlayerList().get(index).getName() + "稳稳将球罚进");
            } else {
                gameResult.addProcess(club1.getPlayerList().get(index).getName() + "竟然把球罚丢了");
            }

            index = (index + 1) % 3;
        }
        System.out.println("（点球） " + club1.getName() + " " + penalty1 + " ： " + penalty2 + " " + club2.getName() + " （点球）");
        if(penalty1>penalty2){
            return club1;
        }else {
            return club2;
        }


    }

    boolean getPenalty(double attack, double keeper){
        Random random =new Random();

        double possibility=random.nextDouble()*100;

        if(possibility<=attack-keeper){
            return true;
        }
        return false;
    }

}

