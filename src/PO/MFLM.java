package PO;

import java.util.ArrayList;
import java.util.Random;

public class MFLM extends Match {

    final int clubNumber=8;             //注意无论怎么样，该值必须为正偶数，如果是奇数，请务必自己重写交换主客场位置的那一部分

    private ArrayList<Club> clubList;

    private int[] counterpartTable;

    private MFLMLeagueTable leagueTable;




    public MFLM(int season, int time, ArrayList<Club> clubList ){
        gameType=GameType.MFLM;
        this.season=season;
        this.time=time;
        this.clubList=clubList;
        this.turn=0;

        counterpartTable=new int[clubNumber+1];

        String[] clubNameList=new String[clubNumber];

        for(int i=0;i<clubNumber;i++){
            counterpartTable[i]=i;
            clubNameList[i]=clubList.get(i).getName();
        }
        counterpartTable[clubNumber]=-1;

        leagueTable=new MFLMLeagueTable(clubNameList);

        //随机选择一个轮次开始赛事
        Random random =new Random();
        double times=random.nextDouble()*(clubNumber-1)*2;

        for(int i=0;i<times;i++){
            nextCPT();
        }

    }


    @Override
    public void runThisGame() {
        ArrayList<Game> gameList=new ArrayList<Game>();
        /*前半程一共奇数个轮次，偶数轮交换主客场（2、4、6、……）；
        * 后半程也是奇数个轮次，排布与前半程相同，为了与前半程反转主客场情况，恰好也是偶数轮次交换主客场（8、10、12、……）*/
        if(turn%2==0){
            //偶数回合为了避免一直主场或一直客场，交换主客场位置
            for(int i=0;i<clubNumber/2;i++){
                Game game=new Game("",gameType,season,time,turn,clubList.get(counterpartTable[clubNumber-i-1]),clubList.get(counterpartTable[i]),true,false);
                processGameResult(counterpartTable[clubNumber-i-1],counterpartTable[i],game.startGame());
            }
        }else{
            //奇数回合正常
            for(int i=0;i<clubNumber/2;i++){
                Game game=new Game("",gameType,season,time,turn,clubList.get(counterpartTable[i]),clubList.get(counterpartTable[clubNumber-i-1]),true,false);
                processGameResult(counterpartTable[i],counterpartTable[clubNumber-i-1],game.startGame());
            }
        }

        //本轮全部结束
        addTurn();

        nextCPT();      //排下一轮对阵情况

        printLeagueTable();
    }

    protected void processGameResult(int clubOneIndex, int clubTwoIndex, GameResult gameResult) {
        leagueTable.change(clubOneIndex,gameResult.getClubOneGoal(),gameResult.getClubTwoGoal());
        leagueTable.change(clubTwoIndex,gameResult.getClubTwoGoal(),gameResult.getClubOneGoal());
    }

    private void nextCPT(){
        /*排布下一场对阵表*/
        for(int i=clubNumber;i>1;i--){
            counterpartTable[i]=counterpartTable[i-1];
        }

        counterpartTable[1]=counterpartTable[clubNumber];

        //貌似排完了？YES!


    }

    private void printLeagueTable(){
        System.out.println("第"+turn+"轮");
        for(int i=0;i<clubNumber;i++){
            LeagueTableCell cell=leagueTable.cellList.get(i);
            System.out.println(cell.getClubName()+" "+cell.getHasPlayed()+" "+cell.getWins()+" "+cell.getDraws()+" "+cell.getLoses()+" "+cell.getGoals()+" "+cell.getLosts()+" "+cell.getScore());
        }
        System.out.println("**************************************************");
        System.out.println();
    }


}
