package PO;

public class Pair {

    private Club club1;

    private Club club2;

    private int clubOneHomeScore;       //club1的主场进球数

    private int clubTwoHomeScore;

    private int clubOneAwayScore;       //club1的客场进球数

    private int clubTwoAwayScore;

    private boolean isHomeAdvantage;    //是否有主场优势

    public Pair(Club club1, Club club2, boolean isHomeAdvantage) {
        this.club1 = club1;
        this.club2 = club2;
        this.isHomeAdvantage = isHomeAdvantage;

        clubOneHomeScore = 0;
        clubOneAwayScore = 0;
        clubTwoHomeScore = 0;
        clubTwoAwayScore = 0;
    }

    public void change(int home, int clubOneGoal, int clubTwoGoal) {
        if(isHomeAdvantage&&home!=0) {
            if (home == 1) {
                clubOneHomeScore += clubOneGoal;
                clubTwoAwayScore += clubTwoGoal;
            } else {
                clubOneAwayScore += clubOneGoal;
                clubTwoHomeScore += clubTwoGoal;
            }
        }else{
            clubOneHomeScore += clubOneGoal;
            clubTwoHomeScore += clubTwoGoal;
        }
    }

    public Club getWinner() {
        if ((clubOneHomeScore + clubOneAwayScore) > (clubTwoHomeScore + clubTwoAwayScore)) {
            return club1;
        } else if ((clubOneHomeScore + clubOneAwayScore) < (clubTwoHomeScore + clubTwoAwayScore)) {
            return club2;
        } else {
            if (clubOneAwayScore > clubTwoAwayScore) {
                return club1;
            } else if (clubOneAwayScore < clubTwoAwayScore) {
                return club2;
            }
        }
        return null;       //null表示不分胜负，需要加赛或者点球大战，具体由调用方处理
    }

    public Club getClub(int index){
        if(index==1){
            return club1;
        }else{
            return club2;
        }
    }

}
