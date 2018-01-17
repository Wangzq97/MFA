import PO.*;
import PO.Matchs.MC;
import PO.Matchs.MFLM;
import PO.Matchs.NoMoreGameException;
import UI.ui;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.oracle.jrockit.jfr.ContentType.Bytes;

public class start {

    public static void main(String[] args){
        ArrayList<Club> clubList=new ArrayList<Club>();
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
        clubList.add(club1);

        Club club2=new Club();

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
        clubList.add(club2);

        Club club3=new Club();

        club3.setName("广州恒大");

        ArrayList<Player> clubThreePlayerList=new ArrayList<Player>();

        Player player21=new Player("广州恒大","S09", "Gao Lin", Location.forward, 63.3,0.0, 66.0);
        clubThreePlayerList.add(player21);
        Player player22=new Player("广州恒大","S23", "J. Martínez", Location.forward, 75.2,0.0, 82.0);
        clubThreePlayerList.add(player22);
        Player player23=new Player("广州恒大","M01", "Zhen Zhi", Location.midfield, 60.9,4.4, 75.0);
        clubThreePlayerList.add(player23);
        Player player24=new Player("广州恒大","D12", "Zhang Linfan", Location.back, 0.0,7.6, 70.0);
        clubThreePlayerList.add(player24);
        Player player25=new Player("广州恒大","D04", "Feng Xiaoting", Location.back, 0.0,7.6, 70.0);
        clubThreePlayerList.add(player25);
        Player player26=new Player("广州恒大","GK04", "Zen Cheng", Location.goalkeeper, 0.0,21.5, 70.0);
        clubThreePlayerList.add(player26);

        club3.setPlayers(clubThreePlayerList);
        clubList.add(club3);

        Club club4=new Club();

        club4.setName("AC米兰");

        ArrayList<Player> clubFourPlayerList=new ArrayList<Player>();

        Player player31=new Player("AC米兰","S09", "Morata", Location.forward, 73.1,0.0, 80.0);
        clubFourPlayerList.add(player31);
        Player player32=new Player("AC米兰","S23", "A.Silva", Location.forward, 75.2,0.0, 82.0);
        clubFourPlayerList.add(player32);
        Player player33=new Player("AC米兰","M01", "Conti", Location.midfield, 67.9,5.4, 85.0);
        clubFourPlayerList.add(player33);
        Player player34=new Player("AC米兰","D12", "Bonucci", Location.back, 0.0,10.1, 80.0);
        clubFourPlayerList.add(player34);
        Player player35=new Player("AC米兰","D04", "Romagnoli", Location.back, 0.0,10.8, 83.0);
        clubFourPlayerList.add(player35);
        Player player36=new Player("AC米兰","GK04", "Donnarumma", Location.goalkeeper, 0.0,31, 88.0);
        clubFourPlayerList.add(player36);

        club4.setPlayers(clubFourPlayerList);
        clubList.add(club4);

        Club club5=new Club();
        club5.setName("国际米兰");

        ArrayList<Player> clubFivePlayerList=new ArrayList<Player>();

        Player player41=new Player("国际米兰","S09", "M. Icardi", Location.forward, 78.6,0.0, 88.0);
        clubFivePlayerList.add(player41);
        Player player42=new Player("国际米兰","S23", "I. Perisic", Location.forward, 75.2,0.0, 82.0);
        clubFivePlayerList.add(player42);
        Player player43=new Player("国际米兰","M01", "J. Mario", Location.midfield, 67.9,5.4, 85.0);
        clubFivePlayerList.add(player43);
        Player player44=new Player("国际米兰","D12", "A. Ranocchia", Location.back, 0.0,10.1, 80.0);
        clubFivePlayerList.add(player44);
        Player player45=new Player("国际米兰","D04", "J. Cancelo", Location.back, 0.0,10.8, 83.0);
        clubFivePlayerList.add(player45);
        Player player46=new Player("国际米兰","GK04", "S. Handanovic", Location.goalkeeper, 0.0,28.0, 85.0);
        clubFivePlayerList.add(player46);

        club5.setPlayers(clubFivePlayerList);
        clubList.add(club5);



        Club club6=new Club();
        club6.setName("巴黎圣日耳曼");

        ArrayList<Player> clubSixPlayerList=new ArrayList<Player>();

        Player player51=new Player("巴黎圣日耳曼","S09", "Neymar JR", Location.forward, 81.0,0.0, 91.0);
        clubSixPlayerList.add(player51);
        Player player52=new Player("巴黎圣日耳曼","S23", "Cavani", Location.forward, 78.6,0.0, 88.0);
        clubSixPlayerList.add(player52);
        Player player53=new Player("巴黎圣日耳曼","M01", "Verrati", Location.midfield, 67.9,5.4, 85.0);
        clubSixPlayerList.add(player53);
        Player player54=new Player("巴黎圣日耳曼","D12", "T. Silva", Location.back, 0.0,10.8, 83.0);
        clubSixPlayerList.add(player54);
        Player player55=new Player("巴黎圣日耳曼","D04", "Marquinhos", Location.back, 0.0,10.8, 83.0);
        clubSixPlayerList.add(player55);
        Player player56=new Player("巴黎圣日耳曼","GK04", "Trapp", Location.goalkeeper, 0.0,30.0, 85.0);
        clubSixPlayerList.add(player56);

        club6.setPlayers(clubSixPlayerList);
        clubList.add(club6);



        Club club7=new Club();
        club7.setName("曼彻斯特城");


        ArrayList<Player> clubSevenPlayerList=new ArrayList<Player>();

        Player player71=new Player("曼彻斯特城","S09", "Sergio Agüero", Location.forward, 78.6,0.0, 88.0);
        clubSevenPlayerList.add(player71);
        Player player72=new Player("曼彻斯特城","S23", "Raheem Sterling", Location.forward, 75.2,0.0, 82.0);
        clubSevenPlayerList.add(player72);
        Player player73=new Player("曼彻斯特城","M01", "Kevin De Bruyne", Location.midfield, 72.0,5.7, 88.0);
        clubSevenPlayerList.add(player73);
        Player player74=new Player("曼彻斯特城","D12", "Vincent Kompany", Location.back, 0.0,11.3, 85.0);
        clubSevenPlayerList.add(player74);
        Player player75=new Player("曼彻斯特城","D04", "John Stones", Location.back, 0.0,11.3, 85.0);
        clubSevenPlayerList.add(player75);
        Player player76=new Player("曼彻斯特城","GK04", "Claudio Bravo", Location.goalkeeper, 0.0,31, 88.0);
        clubSevenPlayerList.add(player76);

        club7.setPlayers(clubSevenPlayerList);
        clubList.add(club7);


        Club club8=new Club();
        club8.setName("莫斯科中央陆军");

        ArrayList<Player> clubEightPlayerList=new ArrayList<Player>();

        Player player81=new Player("莫斯科中央陆军","S09", "Carlos Strandberg", Location.forward, 66.1,0.0, 70.0);
        clubEightPlayerList.add(player81);
        Player player82=new Player("莫斯科中央陆军","S23", "Aaron Olanare", Location.forward, 68.2,0.0, 72.0);
        clubEightPlayerList.add(player82);
        Player player83=new Player("莫斯科中央陆军","M01", "Aleksandr Golovin", Location.midfield, 60.9,4.4, 75.0);
        clubEightPlayerList.add(player83);
        Player player84=new Player("莫斯科中央陆军","D12", "Mario Fernandes", Location.back, 0.0,7.8, 70.0);
        clubEightPlayerList.add(player84);
        Player player85=new Player("莫斯科中央陆军","D04", "Georgy Shennikov", Location.back, 0.0,8.3, 73.0);
        clubEightPlayerList.add(player85);
        Player player86=new Player("莫斯科中央陆军","GK04", "Igor Vladimirovich Akinfeev", Location.goalkeeper, 0.0,28, 78.0);
        clubEightPlayerList.add(player86);

        club8.setPlayers(clubEightPlayerList);
        clubList.add(club8);
        new ui(clubList);

        /*MFLM mflm=new MFLM(1,1,clubList);
        for(int i=0;i<7;i++) {
            mflm.runThisGame();
        }

        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        ObjectOutputStream oos2 = null;
        try {
            oos2 = new ObjectOutputStream(byteOutStream);
            oos2.writeObject(mflm);
            oos2.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("worm.out"));
            oos1.writeObject(byteOutStream.toByteArray());//必须所有引用的对象都实现序列化，否则抛出有java.io.NotSerializableException:这个异常
            oos1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(byteOutStream.toByteArray());*/
        /*byte[] bytes=null;
        MFLM smflm=null;
        File f = new File("C:\\Users\\wangz\\Documents\\MFA\\src\\saves\\saves.out");

        ObjectInputStream ois2 = null;
        try {
            ois2 = new ObjectInputStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bytes = (byte[])ois2.readObject();
            ois2.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ByteArrayInputStream byteInStream = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream ois3 = new ObjectInputStream(byteInStream);
            smflm= (MFLM)ois3.readObject();
            ois2.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        for(int i=0;i<7;i++) {
            smflm.runThisGame();
        }*/



        /*MC mc=new MC(1,1,clubList);
        try {
            while(mc.run());
            mc.run();
        } catch (NoMoreGameException e) {
            e.printStackTrace();
        }*/

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
