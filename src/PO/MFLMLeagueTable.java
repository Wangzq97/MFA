package PO;

import java.util.ArrayList;

public class MFLMLeagueTable extends LeagueTable {

    public MFLMLeagueTable(String[] clubList){
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

    }
}
