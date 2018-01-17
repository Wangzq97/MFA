package PO.Matchs;

public class NoMoreGameException extends Exception {
    public NoMoreGameException(){
        super("没有多余的赛程，请检查你的赛程规划是否有误");
    }

}
