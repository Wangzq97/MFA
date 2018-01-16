package PO.Matchs;

import PO.LeagueTable;
import PO.LeagueTableCell;

import java.util.ArrayList;

public class MFLMLeagueTable extends LeagueTable {

    public MFLMLeagueTable(String[] clubList){
        numbers=8;
        printList=new ArrayList<LeagueTableCell>();
        cellList=new ArrayList<LeagueTableCell>();
        LeagueTableCell cell=null;
        for(int i=0;i<clubList.length;i++){
            cell= new LeagueTableCell(clubList[i]);
            cellList.add(cell);
        }
    }

    public void change(int index, int goal, int lost){
        cellList.get(index).change(goal,lost);
    }

    @Override
    protected void sort() {
        printList.clear();
        for(int i=0;i<numbers;i++){
            printList.add(cellList.get(i));
        }

        //冒泡排个序

        LeagueTableCell cell=null;
        for(int i=0;i<numbers;i++){
            for(int j=0;j<i;j++){
                if(printList.get(i).getScore()==printList.get(j).getScore()){                       //积分相同，比较净胜球
                    if((printList.get(i).getGoals()-printList.get(i).getLosts())>(printList.get(j).getGoals()-printList.get(j).getLosts())){
                        cell=printList.get(i);
                        printList.set(i,printList.get(j));
                        printList.set(j,cell);
                    }else if((printList.get(i).getGoals()-printList.get(i).getLosts())==(printList.get(j).getGoals()-printList.get(j).getLosts())){         //如果净胜球也相同，比较进球数
                        if(printList.get(i).getGoals()>printList.get(j).getGoals()){
                            cell=printList.get(i);
                            printList.set(i,printList.get(j));
                            printList.set(j,cell);
                        }
                    }
                }
                if(printList.get(i).getScore()>printList.get(j).getScore()){
                    cell=printList.get(i);
                    printList.set(i,printList.get(j));
                    printList.set(j,cell);
                }
            }
        }
    }

    public ArrayList<LeagueTableCell> getPrintList(){
        return printList;
    }

}
