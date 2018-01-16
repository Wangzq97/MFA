package PO.Games;

import PO.*;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private String gameName;

    private GameType gameType;

    private int season;         //第几赛季

    private int time;           //第几届赛事

    private int turn;           //第几轮（比赛日）

    private Club club1;

    private Club club2;

    private boolean homeAdvantage;

    private boolean penalty;

    private int clubOneGoal;

    private int clubTwoGoal;

    private GameResult gameResult;

    ArrayList<Player> clubOnePlayerList;

    ArrayList<Player> clubTwoPlayerList;

    public Game(String gameName, GameType gameType, int season, int time, int turn, Club club1, Club club2, boolean homeAdvantage, boolean penalty){
        //理论上这些信息在初始化时已经定好，不可能再修改

        this.gameName=gameName;
        this.gameType=gameType;
        this.season=season;
        this.time=time;
        this.turn=turn;
        this.club1=club1;
        this.club2=club2;
        this.homeAdvantage=homeAdvantage;
        this.penalty=penalty;
        clubOneGoal=0;
        clubTwoGoal=0;

        gameResult=new GameResult(gameName, gameType, season, time, turn, club1, club2, homeAdvantage, penalty);
    }

    public GameResult startGame(){


        //club1

        clubOnePlayerList=club1.getPlayerList();

       double clubOneDefender=clubOnePlayerList.get(2).getDefenderAbility()+clubOnePlayerList.get(3).getDefenderAbility()+clubOnePlayerList.get(4).getDefenderAbility();

       double clubOneKeeper=clubOnePlayerList.get(5).getDefenderAbility();

       if(homeAdvantage) {
           clubOneDefender=clubOneDefender*1.1;
           clubOneKeeper=clubOneKeeper*1.1;
       }


        //club2

        clubTwoPlayerList=club2.getPlayerList();

        double clubTwoDefender=clubTwoPlayerList.get(2).getDefenderAbility()+clubTwoPlayerList.get(3).getDefenderAbility()+clubTwoPlayerList.get(4).getDefenderAbility();

        double clubTwoKeeper=clubTwoPlayerList.get(5).getDefenderAbility();


        //start

        clubOneGoal=0;
        clubTwoGoal=0;
        getClubOneGoal(0,clubTwoDefender,clubTwoKeeper);

        getClubTwoGoal(0,clubOneDefender,clubOneKeeper);

        getClubOneGoal(1,clubTwoDefender,clubTwoKeeper);

        getClubTwoGoal(1,clubOneDefender,clubOneKeeper);

        getClubOneGoal(2,clubTwoDefender,clubTwoKeeper);

        getClubTwoGoal(2,clubOneDefender,clubOneKeeper);

        if(clubOneGoal==clubTwoGoal&&penalty){
            gameResult.addProcess("******************************************************************************");
            penalty(clubOneKeeper,clubTwoKeeper);
        }else{
            gameResult.addProcess(club1.getName()+" "+clubOneGoal+" ： "+clubTwoGoal+" "+club2.getName());
        }
        gameResult.addProcess(gameType.toString()+"——"+gameName+"全场比赛结束");

        return this.gameResult;
        }

    void getClubOneGoal(int playNum,double defender, double keeper){
        Player player =clubOnePlayerList.get(playNum);
        String name=player.getName();
        int goalNumber=0;

        double attack=player.getAttackAbility();


        if(homeAdvantage) {
            attack=attack*1.1;
        }

        int chances=2;

        if(player.getLocation()== Location.midfield){chances=1;}

        int go=3;

        for(int i=0;i<chances;i++){
            go=3;
            while(go==3){
                go=getGoal(attack,defender,keeper);
                if(go>=2){
                    clubOneGoal++;
                    goalNumber++;
                    gameResult.addProcess(club1.getName()+"的"+player.getName()+"打进个人第"+goalNumber+"球");
                    gameResult.goal(1,playNum);
                    if(go==3){
                        gameResult.addProcess(club1.getName()+"的"+player.getName()+"凭借自己高超的技巧获得了新的机会");
                    }
                } else if(go==0){
                    Random random =new Random();

                    double possibility=random.nextDouble()*defender;

                    if(possibility<club2.getPlayerList().get(2).getDefenderAbility()){
                        gameResult.defend(2,0);
                        gameResult.addProcess(club1.getName()+"的"+player.getName()+"被"+club2.getName()+"的"+club2.getPlayerList().get(2).getName()+"断下球权");
                    }else if((possibility<club2.getPlayerList().get(2).getDefenderAbility()+club2.getPlayerList().get(3).getDefenderAbility())) {
                        gameResult.defend(2, 1);
                        gameResult.addProcess(club1.getName()+"的"+player.getName()+"被"+club2.getName()+"的"+club2.getPlayerList().get(3).getName()+"断下球权");
                    }else{
                        gameResult.defend(2,2);
                        gameResult.addProcess(club1.getName()+"的"+player.getName()+"被"+club2.getName()+"的"+club2.getPlayerList().get(4).getName()+"断下球权");
                    }
                }else {
                    gameResult.save(2);
                    gameResult.addProcess(club1.getName()+"的"+player.getName()+"的射门被"+club2.getName()+"的"+club2.getPlayerList().get(5).getName()+"奋力扑出");
                }
            }
        }

    }


    void getClubTwoGoal(int playNum,double defender, double keeper){
        Player player =clubTwoPlayerList.get(playNum);
        String name=player.getName();
        int goalNumber=0;

        double attack=player.getAttackAbility();

        int chances=2;

        if(player.getLocation()==Location.midfield){chances=1;}

        int go=3;

        for(int i=0;i<chances;i++){
            go=3;
            while(go==3){
                go=getGoal(attack,defender,keeper);
                if(go>=2){
                    clubTwoGoal++;
                    goalNumber++;
                    gameResult.addProcess(club2.getName()+"的"+player.getName()+"打进个人第"+goalNumber+"球");
                    gameResult.goal(2,playNum);
                    if(go==3){
                        gameResult.addProcess(club2.getName()+"的"+player.getName()+"凭借自己高超的技巧获得了新的机会");
                    }
                } else if(go==0){
                    Random random =new Random();

                    double possibility=random.nextDouble()*defender;

                    if(possibility<club1.getPlayerList().get(3).getDefenderAbility()){
                        gameResult.defend(1,0);
                        gameResult.addProcess(club2.getName()+"的"+player.getName()+"被"+club1.getName()+"的"+club1.getPlayerList().get(2).getName()+"断下球权");
                    }else if((possibility<club1.getPlayerList().get(2).getDefenderAbility()+club1.getPlayerList().get(2).getDefenderAbility())) {
                        gameResult.defend(1, 1);
                        gameResult.addProcess(club2.getName()+"的"+player.getName()+"被"+club1.getName()+"的"+club1.getPlayerList().get(3).getName()+"断下球权");
                    }else{
                        gameResult.defend(1,2);
                        gameResult.addProcess(club2.getName()+"的"+player.getName()+"被"+club1.getName()+"的"+club1.getPlayerList().get(4).getName()+"断下球权");
                    }
                }else {
                    gameResult.save(1);
                    gameResult.addProcess(club2.getName()+"的"+player.getName()+"的射门被"+club1.getName()+"的"+club1.getPlayerList().get(5).getName()+"奋力扑出");
                }
            }
        }

    }


    int getGoal(double attack,double defender, double keeper){

        Random random =new Random();

        double possibility=random.nextDouble()*100;

        if(possibility>=attack-defender){
            return 0;
        }

        possibility=random.nextDouble()*100;
        if(possibility<keeper){
            return 1;
        }

        possibility=random.nextDouble()*1000;
        if(possibility<=attack){
            return 3;
        }else{
            return 2;
        }

    }


    void penalty(double keeper1, double keeper2){
        int penalty1=0;
        int penalty2=0;

        int index=2;           //罚球球员序号

        //最先的5次
        for(int i=0;i<5;i++) {
            if (getPenalty(club1.getPlayerList().get(i % 3).getAttackAbility(), keeper2/2)) {
                gameResult.penalty(1);
                gameResult.addProcess(club1.getPlayerList().get(i % 3).getName() + "稳稳将球罚进");
            } else {
                gameResult.addProcess(club1.getPlayerList().get(i % 3).getName() + "竟然把球罚丢了");
            }

            if ((penalty1 - penalty2) > (5 - i) || (penalty2 - penalty1) > (4 - i)) {
                gameResult.addProcess(club1.getName()+" "+clubOneGoal+" ： "+clubTwoGoal+" "+club2.getName());
                gameResult.addProcess("（点球） " + club1.getName() + " " + penalty1 + " ： " + penalty2 + " " + club2.getName() + " （点球）");
                return;
            }

            if (getPenalty(club2.getPlayerList().get(i % 3).getAttackAbility(), keeper1/2)) {
                gameResult.penalty(2);
                gameResult.addProcess(club2.getPlayerList().get(i % 3).getName() + "稳稳将球罚进");
            } else {
                gameResult.addProcess(club2.getPlayerList().get(i % 3).getName() + "竟然把球罚丢了");
            }

            if ((penalty1 - penalty2) > (4 - i) || (penalty2 - penalty1) > (4 - i)) {
                gameResult.addProcess(club1.getName()+" "+clubOneGoal+" ： "+clubTwoGoal+" "+club2.getName());
                gameResult.addProcess("（点球） " + club1.getName() + " " + penalty1 + " ： " + penalty2 + " " + club2.getName() + " （点球）");
                return;
            }
        }

            while(penalty1==penalty2){
                if(getPenalty(club1.getPlayerList().get(index).getAttackAbility(),keeper2/2)){
                    gameResult.penalty(1);
                    gameResult.addProcess(club1.getPlayerList().get(index).getName()+"稳稳将球罚进");
                }else{
                    gameResult.addProcess(club1.getPlayerList().get(index).getName()+"竟然把球罚丢了");
                }
                if(getPenalty(club2.getPlayerList().get(index).getAttackAbility(),keeper1/2)){
                    gameResult.penalty(2);
                    gameResult.addProcess(club2.getPlayerList().get(index).getName()+"稳稳将球罚进");
                }else{
                    gameResult.addProcess(club1.getPlayerList().get(index).getName()+"竟然把球罚丢了");
                }

                index=(index+1)%3;
            }
            System.out.println(club1.getName()+" "+clubOneGoal+" ： "+clubTwoGoal+" "+club2.getName());
            System.out.println("（点球） "+club1.getName()+" "+penalty1+" ： "+penalty2+" "+club2.getName()+" （点球）");
            return;



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
