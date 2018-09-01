package PO.Matchs;

import PO.Club;
import PO.Games.Game;
import PO.Games.GameResult;
import PO.Games.GameType;
import PO.Games.Penalty;
import PO.Pair;
import java.util.ArrayList;
import java.util.Random;

public class MC extends Match{
    final int clubNumber=8;             //注意无论怎么样，该值必须为2的x（x>=1)次方，否则……

    private int leftNumber;             //剩余队伍数（可能有用，先写着）

    private boolean isEnd;

    private ArrayList<Club> clubList;
    private ArrayList<Pair> pairList;

    private String message="";

    public MC(int season, int time, ArrayList<Club> clubList){
        gameType= GameType.MC;
        this.season=season;
        this.time=time;
        this.clubList=clubList;
        this.turn=0;
        leftNumber=clubNumber;
        isEnd=false;
    }

    public boolean run() throws NoMoreGameException {
        if(!isEnd){
            runThisGame();
            if(!isEnd) return true;
        }else{
            throw new NoMoreGameException();
        }
        return false;
    }



    @Override
    public void runThisGame() {
        if(turn%2==0){
            //偶数轮说明要重新抽签
            pairList=new ArrayList<Pair>();
            Random random =new Random();

            boolean isHomeAdvantage=true;

            if(leftNumber==2){
                isHomeAdvantage=false;
            }

            while(leftNumber>0) {
                int index1 = (int) (random.nextDouble() * (leftNumber - 1));
                leftNumber--;
                Club club1=clubList.get(index1);
                clubList.remove(index1);
                int index2 = (int) (random.nextDouble() * (leftNumber - 1));
                leftNumber--;
                Club club2=clubList.get(index2);
                clubList.remove(index2);
                Pair pair=new Pair(club1,club2,isHomeAdvantage);
                pairList.add(pair);
            }

            if(pairList.size()>1) {
                for(int i=0;i<pairList.size();i++){
                    Club club1=pairList.get(i).getClub(1);
                    Club club2=pairList.get(i).getClub(2);
                    String gameName="明月杯第"+(turn+1)+"比赛日1/"+leftNumber/2+"决赛第一回合第"+(i+1)+"场——"+club1.getName()+"对阵"+club2.getName();
                    Game game=new Game(gameName,gameType,season,time,turn,club1,club2,true,false);
                    processGameResult(pairList.get(i),1,game.startGame(),false);
                }
            }else{
                //pair只有一个了，说明这场是决赛了
                System.out.println("FINAL");
                Club club1=pairList.get(0).getClub(1);
                Club club2=pairList.get(0).getClub(2);
                String gameName="明月杯第"+(turn+1)+"比赛日"+"决赛"+"——"+club1.getName()+"对阵"+club2.getName();
                Game game=new Game(gameName,gameType,season,time,turn,club1,club2,false,true);
                processGameResult(pairList.get(0),0,game.startGame(),false);
                Club winner = pairList.get(0).getWinner();
                if (winner == null) {
                    System.out.println("Penalty");
                    //winner = pairList.get(i).getClub(1);         //嗯？大雾，逃……还没写这块，这块应该放个只打点球的比赛
                    Penalty penalty = new Penalty(pairList.get(0).getClub(1), pairList.get(0).getClub(2), gameType, season, time, turn);
                    winner = penalty.getWinner();
                    System.out.println(penalty.getGameResultProgress());
                    message+=penalty.getGameResultProgress();
                }
                isEnd=true;
            }



        }else{
                for (int i = 0; i < pairList.size(); i++) {
                    Club club1=pairList.get(i).getClub(1);
                    Club club2=pairList.get(i).getClub(2);
                    String gameName="明月杯第"+(turn+1)+"比赛日1/"+leftNumber/2+"决赛第二回合第"+(i+1)+"场——"+club2.getName()+"对阵"+club1.getName();
                    Game game = new Game(gameName, gameType, season, time, turn, club2, club1, true, false);
                    processGameResult(pairList.get(i), 2, game.startGame(),true);
                    Club winner = pairList.get(i).getWinner();
                    if (winner == null) {
                        System.out.println("Penalty");
                        //winner = pairList.get(i).getClub(1);         //嗯？大雾，逃……还没写这块，这块应该放个只打点球的比赛
                        Penalty penalty = new Penalty(pairList.get(i).getClub(1), pairList.get(i).getClub(2), gameType, season, time, turn);
                        winner = penalty.getWinner();
                        System.out.println(penalty.getGameResultProgress());
                        message+=penalty.getGameResultProgress();
                    }

                    clubList.add(winner);
                    leftNumber++;
                }

        }
        addTurn();
    }

    private void processGameResult(Pair pair,int home,GameResult gameResult,boolean needExchange){
        if(needExchange) {
            pair.change(home, gameResult.getClubTwoGoal(), gameResult.getClubOneGoal());
        }else{
            pair.change(home, gameResult.getClubOneGoal(), gameResult.getClubTwoGoal());
        }
        System.out.println(gameResult.getGameProcess());
        message+=gameResult.getGameProcess();
    }
}
