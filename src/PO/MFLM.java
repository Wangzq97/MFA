package PO;

import java.util.ArrayList;

public class MFLM extends Match {

    final int clubNumber=8;             //注意无论怎么样，该值必须为正偶数

    private ArrayList<Club> clubList;

    private int[] counterpartTable;




    public MFLM(int season, int time, ArrayList<Club> clubList ){
        gameType=GameType.MFLM;
        this.season=season;
        this.time=time;
        this.clubList=clubList;
        this.turn=1;

        counterpartTable=new int[clubNumber+1];

        for(int i=0;i<clubNumber;i++){
            counterpartTable[i]=i;
        }
        counterpartTable[clubNumber]=-1;

    }


    @Override
    public void runThisGame() {
        ArrayList<Game> gameList=new ArrayList<Game>();

        if(turn%2==0){
            //偶数回合为了避免一直主场或一直客场，交换主客场位置
            for(int i=0;i<clubNumber/2;i++){
                Game game=new Game("",gameType,clubList.get(counterpartTable[clubNumber-i-1]),clubList.get(counterpartTable[i]),true,false);
                game.startGame();
            }
        }else{
            //奇数回合正常
            for(int i=0;i<clubNumber/2;i++){
                Game game=new Game("",gameType,clubList.get(counterpartTable[i]),clubList.get(counterpartTable[clubNumber-i-1]),true,false);
                game.startGame();
            }
        }

        //本轮全部结束
        addTurn();

        nextCPT();      //排下一轮对阵情况
    }

    private void nextCPT(){
        /*排布下一场对阵表*/
        for(int i=clubNumber;i>1;i--){
            counterpartTable[i]=counterpartTable[i-1];
        }

        counterpartTable[1]=counterpartTable[clubNumber];

        //貌似排完了？


    }


}
