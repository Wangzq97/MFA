package PO.Matchs;

import PO.Games.GameType;

abstract class Match {

    protected GameType gameType;  //比赛类型

    protected int season;         //第几赛季

    protected int time;           //第几届赛事

    protected int turn;           //第几轮（比赛日）

    public abstract void runThisGame();     //开始本轮所有比赛

    protected void addTurn(){turn++;}

}
