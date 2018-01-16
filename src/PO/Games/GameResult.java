package PO.Games;

import PO.Club;

public class GameResult {

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

    private int clubOnePenalty;

    private int clubTwoPenalty;

    private int[] clubOneGoalsList={0,0,0};             //  俱乐部1各个前锋的进球数据

    private int[] clubTwoGoalsList={0,0,0};

    private int[] clubOneDefendsList={0,0,0};           //  俱乐部1各个后卫的断球数据

    private int[] clubTwoDefendsList={0,0,0};

    private int clubOneSaves=0;                 //  俱乐部1门将的扑救数据

    private int clubTwoSaves=0;

    private String gameProcess="";                 //  比赛情况全记录


    public GameResult(String gameName, GameType gameType,int season, int time, int turn, Club club1,Club club2, boolean homeAdvantage, boolean penalty){
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
        clubOnePenalty=0;
        clubTwoPenalty=0;
    }

    public void goal(int clubNum, int playNum){
        if(clubNum==1){
            clubOneGoal++;
            clubOneGoalsList[playNum]++;
        }else{
            clubTwoGoal++;
            clubTwoGoalsList[playNum]++;
        }
    }

    public void defend(int clubNum, int playNum){
        if(clubNum==1){
            clubOneDefendsList[playNum]++;
        }else{
            clubTwoDefendsList[playNum]++;
        }
    }

    public void save(int clubNum){
        if(clubNum==1){
            clubOneSaves++;
        }else{
            clubTwoSaves++;
        }
    }

    public void penalty(int clubNum){
        if(clubNum==1){
            clubOnePenalty++;
            clubOneGoal++;
            //clubOneGoalsList[playNum]++;
        }else{
            clubTwoPenalty++;
            clubTwoGoal++;
            //clubTwoGoalsList[playNum]++;
        }
    }

    public void addProcess(String newLine){
        this.gameProcess+="\n"+newLine;
    }

    public int getClubOneGoal(){
        return clubOneGoal;
    }

    public int getClubTwoGoal(){
        return clubTwoGoal;
    }

    public int[] getClubOneGoalsList() {
        return clubOneGoalsList;
    }

    public int[] getClubTwoGoalsList() {
        return clubTwoGoalsList;
    }

    public int[] getClubOneDefendsList() {
        return clubOneDefendsList;
    }

    public int[] getClubTwoDefendsList() {
        return clubTwoDefendsList;
    }

    public int getClubOneSaves() {
        return clubOneSaves;
    }

    public int getClubTwoSaves() {
        return clubTwoSaves;
    }

    public String getGameProcess() {
        return gameProcess;
    }
}
