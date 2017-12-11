
public class Player{

    private String name;
    private double score;
    private static int turns;
    private static int maxTurns;

    public static int getTurns() {
        return turns;
    }

    public static void setTurns(int turn) {
        turns = turn;
    }

    public double getScore() {
        return score;
    }

    public static int getMaxTurns() {
        return maxTurns;
    }

    public static void setMaxTurns(int maxTurn) {
        maxTurns = maxTurn;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //These are my player class getters and setters, as well as the base values, with a nice reset player class

    public void Player(){
        this.name = "";
        this.score = 0;
        this.turns = 0;

    }

    public void resetPlayer(Player p){
        p.setName("");
        p.setScore(0);
        p.setTurns(0);
    }

}


