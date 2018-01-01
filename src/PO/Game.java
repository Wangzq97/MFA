package PO;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private String gameName;

    private GameType gameType;

    private Club club1;

    private Club club2;

    private boolean homeAdvantage;

    private boolean penalty;

    private int clubOneGoal;

    private int clubTwoGoal;

    public Game(String gameName, GameType gameType, Club club1, Club club2, boolean homeAdvantage, boolean penalty){
        //理论上这些信息在初始化时已经定好，不可能再修改

        this.gameName=gameName;
        this.gameType=gameType;
        this.club1=club1;
        this.club2=club2;
        this.homeAdvantage=homeAdvantage;
        this.penalty=penalty;
        clubOneGoal=0;
        clubTwoGoal=0;
    }

    public void startGame(){


        //club1

        ArrayList<Player> clubOnePlayerList=club1.getPlayerList();

       double clubOneDefender=clubOnePlayerList.get(2).getDefenderAbility()+clubOnePlayerList.get(3).getDefenderAbility()+clubOnePlayerList.get(4).getDefenderAbility();

       double clubOneKeeper=clubOnePlayerList.get(5).getDefenderAbility();

       if(homeAdvantage) {
           clubOneDefender=clubOneDefender*1.1;
           clubOneKeeper=clubOneKeeper*1.1;
       }


        //club2

        ArrayList<Player> clubTwoPlayerList=club2.getPlayerList();

        double clubTwoDefender=clubTwoPlayerList.get(2).getDefenderAbility()+clubTwoPlayerList.get(3).getDefenderAbility()+clubTwoPlayerList.get(4).getDefenderAbility();

        double clubTwoKeeper=clubTwoPlayerList.get(5).getDefenderAbility();


        //start

        clubOneGoal=0;
        clubTwoGoal=0;
        getClubOneGoal(clubOnePlayerList.get(0),clubTwoDefender,clubTwoKeeper);
        getClubOneGoal(clubOnePlayerList.get(1),clubTwoDefender,clubTwoKeeper);
        getClubOneGoal(clubOnePlayerList.get(2),clubTwoDefender,clubTwoKeeper);

        System.out.println("******************************************************************************");

        getClubTwoGoal(clubTwoPlayerList.get(0),clubOneDefender,clubOneKeeper);
        getClubTwoGoal(clubTwoPlayerList.get(1),clubOneDefender,clubOneKeeper);
        getClubTwoGoal(clubTwoPlayerList.get(2),clubOneDefender,clubOneKeeper);




        if(clubOneGoal==clubTwoGoal&&penalty){
            System.out.println("******************************************************************************");
            penalty(clubOneKeeper,clubTwoKeeper);
        }else{
            System.out.println(club1.getName()+" "+clubOneGoal+" ： "+clubTwoGoal+" "+club2.getName());
        }
        System.out.println(gameType.toString()+"——"+gameName+"全场比赛结束");

        }

    void getClubOneGoal(Player player,double defender, double keeper){
        String name=player.getName();
        int goalNumber=0;

        double attack=player.getAttackAbility();


        if(homeAdvantage) {
            attack=attack*1.1;
        }

        int chances=2;

        if(player.getLocation()==Location.midfield){chances=1;}

        int go=3;

        for(int i=0;i<chances;i++){
            go=3;
            while(go==3){
                go=getGoal(attack,defender,keeper);
                if(go>=2){
                    clubOneGoal++;
                    goalNumber++;
                    System.out.println(player.getName()+"打进个人第"+goalNumber+"球");
                    if(go==3){
                        System.out.println(player.getName()+"凭借自己高超的技巧获得了新的机会");
                    }
                } else if(go==0){
                    System.out.println(player.getName()+"被"+club2.getPlayerList().get(3).getName()+"和"+club2.getPlayerList().get(4).getName()+"夹击断下球权");
                }else {
                    System.out.println(player.getName()+"的射门被"+club2.getPlayerList().get(5).getName()+"奋力扑出");
                }
            }
        }

    }


    void getClubTwoGoal(Player player,double defender, double keeper){
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
                    System.out.println(player.getName()+"打进个人第"+goalNumber+"球");
                    if(go==3){
                        System.out.println(player.getName()+"凭借自己高超的技巧获得了新的机会");
                    }
                } else if(go==0){
                    System.out.println(player.getName()+"被"+club1.getPlayerList().get(3).getName()+"和"+club1.getPlayerList().get(4).getName()+"夹击断下球权");
                }else {
                    System.out.println(player.getName()+"的射门被"+club1.getPlayerList().get(5).getName()+"奋力扑出");
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
                penalty1++;
                System.out.println(club1.getPlayerList().get(i % 3).getName() + "稳稳将球罚进");
            } else {
                System.out.println(club1.getPlayerList().get(i % 3).getName() + "竟然把球罚丢了");
            }

            if ((penalty1 - penalty2) > (5 - i) || (penalty2 - penalty1) > (4 - i)) {
                System.out.println(club1.getName()+" "+clubOneGoal+" ： "+clubTwoGoal+" "+club2.getName());
                System.out.println("（点球） " + club1.getName() + " " + penalty1 + " ： " + penalty2 + " " + club2.getName() + " （点球）");
                return;
            }

            if (getPenalty(club2.getPlayerList().get(i % 3).getAttackAbility(), keeper1/2)) {
                penalty2++;
                System.out.println(club2.getPlayerList().get(i % 3).getName() + "稳稳将球罚进");
            } else {
                System.out.println(club2.getPlayerList().get(i % 3).getName() + "竟然把球罚丢了");
            }

            if ((penalty1 - penalty2) > (4 - i) || (penalty2 - penalty1) > (4 - i)) {
                System.out.println(club1.getName()+" "+clubOneGoal+" ： "+clubTwoGoal+" "+club2.getName());
                System.out.println("（点球） " + club1.getName() + " " + penalty1 + " ： " + penalty2 + " " + club2.getName() + " （点球）");
                return;
            }
        }

            while(penalty1==penalty2){
                if(getPenalty(club1.getPlayerList().get(index).getAttackAbility(),keeper2/2)){
                    penalty1++;
                    System.out.println(club1.getPlayerList().get(index).getName()+"稳稳将球罚进");
                }else{
                    System.out.println(club1.getPlayerList().get(index).getName()+"竟然把球罚丢了");
                }
                if(getPenalty(club2.getPlayerList().get(index).getAttackAbility(),keeper1/2)){
                    penalty2++;
                    System.out.println(club2.getPlayerList().get(index).getName()+"稳稳将球罚进");
                }else{
                    System.out.println(club1.getPlayerList().get(index).getName()+"竟然把球罚丢了");
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
