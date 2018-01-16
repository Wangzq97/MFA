package PO;

import java.util.ArrayList;

public abstract class LeagueTable {

    protected int numbers;              //一共几只队伍

    protected int turns;                //现在是第几轮

    protected ArrayList<LeagueTableCell> cellList;  //原始数据

    protected ArrayList<LeagueTableCell> printList; //排过序的数据

    protected abstract void sort();                 //排个序
}
