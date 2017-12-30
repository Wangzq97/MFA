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
        Player player3=new Player("巴塞罗那","M05", "L. Modrić", Location.midfield, 71.3,5.6, 87.0);
        clubOnePlayerList.add(player3);
        Player player4=new Player("巴塞罗那","D01", "P. Lahm", Location.back, 0.0,12.0, 88.0);
        clubOnePlayerList.add(player4);
        Player player5=new Player("巴塞罗那","D04", "Sergio Ramos", Location.back, 0.0,11.8, 87.0);
        clubOnePlayerList.add(player5);
        Player player6=new Player("巴塞罗那","GK01", "M. Neuer（+1）", Location.goalkeeper, 0.0,35.5, 99.0);
        clubOnePlayerList.add(player6);

        club1.setPlayers(clubOnePlayerList);

        Club club2=new Club();

        club2.setName("拜仁慕尼黑");

        ArrayList<Player> clubTwoPlayerList=new ArrayList<Player>();

        Player player11=new Player("拜仁慕尼黑","S09", "T. Müller", Location.forward, 77.3,0.0, 86.0);
        clubTwoPlayerList.add(player11);
        Player player12=new Player("拜仁慕尼黑","S23", "M. Mandžukić", Location.forward, 75.3,0.0, 83.0);
        clubTwoPlayerList.add(player12);
        Player player13=new Player("拜仁慕尼黑","M01", "Iniesta", Location.midfield, 72.7,5.8, 89.0);
        clubTwoPlayerList.add(player13);
        Player player14=new Player("拜仁慕尼黑","D12", "D. Alaba", Location.back, 0.0,10.8, 83.0);
        clubTwoPlayerList.add(player14);
        Player player15=new Player("拜仁慕尼黑","D04", "Sergio Ramos", Location.back, 0.0,11.8, 87.0);
        clubTwoPlayerList.add(player15);
        Player player16=new Player("拜仁慕尼黑","GK04", "H. Lloris", Location.goalkeeper, 0.0,28.5, 85.0);
        clubTwoPlayerList.add(player16);

        club2.setPlayers(clubTwoPlayerList);


        Game newGame=new Game(" ", GameType.MLM, club1, club2, false, true);

        newGame.startGame();
    }

}
