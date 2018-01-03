package PO;

import java.util.ArrayList;

public abstract class LeagueTable {

    protected int turns;

    protected ArrayList<LeagueTableCell> cellList;

    protected abstract void sort();
}
