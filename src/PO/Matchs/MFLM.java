package PO.Matchs;

import PO.Club;
import PO.Games.Game;
import PO.Games.GameResult;
import PO.Games.GameType;
import PO.LeagueTableCell;
import PO.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class MFLM extends Match{

    final int clubNumber=8;             //注意无论怎么样，该值必须为正偶数，如果是奇数，请务必自己重写交换主客场位置的那一部分

    private ArrayList<Club> clubList;

    private int[] counterpartTable;

    private MFLMLeagueTable leagueTable;

    private boolean isEnd;

    private String message="";

    public MFLMLeagueTable getLeagueTable(){
        return leagueTable;
    }


    public MFLM(int season, int time, ArrayList<Club> clubList ){
        gameType= GameType.MFLM;
        this.season=season;
        this.time=time;
        this.clubList=clubList;
        this.turn=0;
        this.isEnd=false;

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

    public boolean isEnd(){
        return isEnd;
    }


    public String run() throws NoMoreGameException {
        if(!isEnd){
            runThisGame();
        }else{
            throw new NoMoreGameException();
        }
        return message;
    }


    @Override
    public void runThisGame() {
        ArrayList<Game> gameList=new ArrayList<Game>();
        /*前半程一共奇数个轮次，偶数轮交换主客场（2、4、6、……）；
        * 后半程也是奇数个轮次，排布与前半程相同，为了与前半程反转主客场情况，恰好也是偶数轮次交换主客场（8、10、12、……）*/
        if(turn%2==0){
            //偶数回合为了避免一直主场或一直客场，交换主客场位置
            for(int i=0;i<clubNumber/2;i++){
                Club club1=clubList.get(counterpartTable[clubNumber-i-1]);
                Club club2=clubList.get(counterpartTable[i]);
                String gameName="明月足球甲级联赛第"+(turn+1)+"轮第"+(i+1)+"场——"+club1.getName()+"对阵"+club2.getName();
                Game game=new Game(gameName,gameType,season,time,turn,club1,club2,true,false);
                processGameResult(counterpartTable[clubNumber-i-1],counterpartTable[i],game.startGame());
            }
        }else{
            //奇数回合正常
            for(int i=0;i<clubNumber/2;i++){
                Club club1=clubList.get(counterpartTable[i]);
                Club club2=clubList.get(counterpartTable[clubNumber-i-1]);
                String gameName="明月足球甲级联赛第"+(turn+1)+"轮第"+(i+1)+"场——"+club1.getName()+"对阵"+club2.getName();
                Game game=new Game(gameName,gameType,season,time,turn,club1,club2,true,false);
                processGameResult(counterpartTable[i],counterpartTable[clubNumber-i-1],game.startGame());
            }
        }

        //本轮全部结束
        addTurn();

        if(turn>=(clubNumber-1)*2){
            isEnd=true;
        }else{
            nextCPT();      //排下一轮对阵情况
        }
        leagueTable.sort();
        System.out.println();
        System.out.println("**************************************************");
        printLeagueTable();
    }

    protected void processGameResult(int clubOneIndex, int clubTwoIndex, GameResult gameResult) {
        leagueTable.change(clubOneIndex,gameResult.getClubOneGoal(),gameResult.getClubTwoGoal());
        leagueTable.change(clubTwoIndex,gameResult.getClubTwoGoal(),gameResult.getClubOneGoal());
        System.out.println(gameResult.getGameProcess());
        message=message+gameResult.getGameProcess();

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
        System.out.println("MFLM第"+season+"赛季积分榜");
        System.out.println("第"+turn+"轮");
        ArrayList<LeagueTableCell> printList=leagueTable.getPrintList();
        for(int i=0;i<clubNumber;i++){
            LeagueTableCell cell=printList.get(i);
            System.out.println(cell.getClubName()+" "+cell.getHasPlayed()+" "+cell.getWins()+" "+cell.getDraws()+" "+cell.getLoses()+" "+cell.getGoals()+" "+cell.getLosts()+" "+cell.getScore());
        }
        System.out.println("**************************************************");
        System.out.println();
    }

    public void printGoldenGoal(){
        ArrayList<Player> goalList=new ArrayList<Player>();
        for(int i=0;i<clubList.size();i++){
            for(int j=0;j<clubList.get(i).getPlayerList().size();j++){
                goalList.add(clubList.get(i).getPlayerList().get(j));
            }
            goalList.sort(new SortByGoal());
        }
        for(int i=0;i<goalList.size()&&goalList.get(i).getGoal()>0;i++){
            System.out.println("第"+(i+1)+"名："+goalList.get(i).getClub()+"的"+goalList.get(i).getName()+"打进了"+goalList.get(i).getGoal()+"球");
        }


    }

    class SortByGoal implements Comparator {
        public int compare(Object o1, Object o2) {
            Player p1 = (Player) o1;
            Player p2 = (Player) o2;
            if (p1.getGoal() < p2.getGoal()) return 1;
            if (p1.getGoal() == p2.getGoal()) return 0;
            return -1;
        }
    }



}
