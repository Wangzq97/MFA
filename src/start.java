import PO.*;

import java.util.ArrayList;

public class start {

    public static void main(String[] args){

        Club club1=new Club();

        club1.setName("巴塞罗那");

        ArrayList<Player> clubOnePlayerList=new ArrayList<Player>();

        Player player1=new Player("巴塞罗那","S01", "L. Messi", Location.forward, 82.0,0.0, 93.0);
        clubOnePlayerList.add(player1);
        Player player2=new Player("巴塞罗那","S03", "L. Suárez", Location.forward, 79.3,0.0, 89.0);
        clubOnePlayerList.add(player2);
        Player player3=new Player("巴塞罗那","M05", "Iniesta", Location.midfield, 71.3,5.6, 87.0);
        clubOnePlayerList.add(player3);
        Player player4=new Player("巴塞罗那","D01", "Pique", Location.back, 0.0,12.0, 88.0);
        clubOnePlayerList.add(player4);
        Player player5=new Player("巴塞罗那","D04", "J. Alba", Location.back, 0.0,11.5, 86.0);
        clubOnePlayerList.add(player5);
        Player player6=new Player("巴塞罗那","GK01", "M. Ter Stegen", Location.goalkeeper, 0.0,30.5, 87.0);
        clubOnePlayerList.add(player6);

        club1.setPlayers(clubOnePlayerList);

        ArrayList<Club> clubList=new ArrayList<Club>();
        for(int i=0;i<8;i++) {
            Club club= (Club) club1.clone();
            club.setName("巴塞罗那"+i);
            clubList.add(club);
        }

        MFLM mflm=new MFLM(1,1,clubList);
        for(int i=0;i<14;i++) {
            mflm.runThisGame();
        }

        /*Club club2=new Club();

        club2.setName("拜仁慕尼黑");

        ArrayList<Player> clubTwoPlayerList=new ArrayList<Player>();

        Player player11=new Player("拜仁慕尼黑","S09", "T. Müller", Location.forward, 77.3,0.0, 86.0);
        clubTwoPlayerList.add(player11);
        Player player12=new Player("拜仁慕尼黑","S23", "R. Lewandowski", Location.forward, 80.2,0.0, 90.0);
        clubTwoPlayerList.add(player12);
        Player player13=new Player("拜仁慕尼黑","M01", "J. Rodríguez", Location.midfield, 72.7,5.8, 89.0);
        clubTwoPlayerList.add(player13);
        Player player14=new Player("拜仁慕尼黑","D12", "D. Alaba", Location.back, 0.0,11.8, 87.0);
        clubTwoPlayerList.add(player14);
        Player player15=new Player("拜仁慕尼黑","D04", "M. Hummels", Location.back, 0.0,11.8, 87.0);
        clubTwoPlayerList.add(player15);
        Player player16=new Player("拜仁慕尼黑","GK04", "M. Neuer", Location.goalkeeper, 0.0,31.5, 90.0);
        clubTwoPlayerList.add(player16);

        club2.setPlayers(clubTwoPlayerList);


        Game newGame=new Game("第一轮 巴塞罗那VS拜仁慕尼黑", GameType.MFLM, club1, club2, false, true);

        newGame.startGame();*/
    }

}
